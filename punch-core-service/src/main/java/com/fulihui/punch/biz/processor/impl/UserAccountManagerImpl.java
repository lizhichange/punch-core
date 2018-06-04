package com.fulihui.punch.biz.processor.impl;

import static com.fulihui.accountcore.enums.UserWithdrawalStatusEnum.*;
import static com.fulihui.accountcore.enums.WithdrawalsErrorCodeEnum.AMOUNT_LIMIT;
import static com.fulihui.accountcore.enums.WithdrawalsErrorCodeEnum.V2_ACCOUNT_SIMPLE_BAN;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.near.servicesupport.result.TSingleResult;
import org.near.servicesupport.util.ServiceResultUtil;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.fulihui.accountcore.api.UserAccountWithdrawalService;
import com.fulihui.accountcore.dto.UserAccountWithdrawalDTO;
import com.fulihui.accountcore.request.useraccount.UpdateWithdrawalOrderRequest;
import com.fulihui.punch.biz.message.processor.MessageProcessorRegister;
import com.fulihui.punch.biz.processor.UserAccountManager;
import com.fulihui.punch.biz.processor.WechatManager;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.dal.dataobj.TaoKeRecord;
import com.fulihui.punch.dal.mapper.TaoKeRecordMapper;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.weixinclient.request.order.TransfersOrderWeixinRequest;
import com.fulihui.weixinclient.result.WeixinXMLResult;
import com.fulihui.weixinclient.result.order.TransfersOrderWeixinResult;

/**
 * @author Administrator
 */
@Component
public class UserAccountManagerImpl implements UserAccountManager {

    private static final Logger          LOGGER      = LoggerFactory
        .getLogger(UserAccountManagerImpl.class);

    private static final Logger          PAY_MONITOR = LoggerFactory.getLogger("PAY_MONITOR");

    @Autowired
    private UserAccountWithdrawalService userAccountWithdrawalService;

    @Autowired
    private WechatManager                wechatManager;

    @Autowired
    MessageProcessorRegister             messageProcessorRegister;

    @Autowired
    private UserPunchRepository          userPunchRepository;

    @Autowired
    private TransactionTemplate          transactionTemplate;

    @Autowired
    private TaoKeRecordMapper            taoKeRecordMapper;

    @Override
    public void invoke(TransfersOrderWeixinRequest request, String punchRecordId) {
        TransfersOrderWeixinResult wresult = wechatManager.transferInvoke(request);

        // 消息发送成功
        if (wresult != null) {
            boolean result = transactionTemplate.execute(status -> {
                boolean returnCode = false;
                try {
                    String partner_trade_no = request.getPartner_trade_no();
                    UserAccountWithdrawalDTO dto = queryByTradeNo(partner_trade_no);
                    if (dto != null) {
                        Map<String, Object> extInfo = dto.getExtInfo();
                        if (StringUtil.isNotEmpty(punchRecordId)) {
                            String userId = dto.getUserId();
                            UserPunchRecordDTO recordDTO = userPunchRepository
                                .queryByPrimaryKey(punchRecordId, userId);
                            if (recordDTO != null) {
                                //已打卡
                                UpdateWithdrawalOrderRequest updateWithdrawalOrderRequest = new UpdateWithdrawalOrderRequest();
                                updateWithdrawalOrderRequest.setRefund(true);
                                updateWithdrawalOrderRequest.setTradeNo(partner_trade_no);
                                updateWithdrawalOrderRequest.genKey();

                                if (WeixinXMLResult.SUCCESS.equals(wresult.getReturn_code())
                                    && WeixinXMLResult.SUCCESS.equals(wresult.getResult_code())) {
                                    LOGGER.info("微信提现成功,订单号:{}", partner_trade_no);
                                    updateWithdrawalOrderRequest
                                        .setOutTradeNo(wresult.getPayment_no());
                                    updateWithdrawalOrderRequest.setStatus(FINISH.getCode());
                                } else {
                                    String err_code = wresult.getErr_code();
                                    String err_code_des = wresult.getErr_code_des();
                                    if (StringUtil.isEmpty(err_code_des)) {
                                        updateWithdrawalOrderRequest
                                            .setFailureReason("提现失败退款:" + err_code);
                                    } else {
                                        updateWithdrawalOrderRequest
                                            .setFailureReason("提现失败退款:" + err_code_des);
                                    }
                                    Map<String, Object> extInfos = new HashMap<>();
                                    extInfos.put("errCode", err_code);
                                    updateWithdrawalOrderRequest.setExtInfo(extInfo);
                                    if (V2_ACCOUNT_SIMPLE_BAN.getCode().equals(err_code)
                                        || V2_ACCOUNT_SIMPLE_BAN.getDesc().equals(err_code)
                                        || AMOUNT_LIMIT.getCode().equals(err_code)) {
                                        updateWithdrawalOrderRequest.setStatus(ERROR.getCode());
                                    } else {
                                        updateWithdrawalOrderRequest
                                            .setStatus(WITHDRAWAL.getCode());
                                    }
                                    LOGGER.info("微信提现失败,订单号:{},errCode:{}", partner_trade_no,
                                        wresult.getErr_code());

                                }

                                LOGGER.info("updateWithdrawalOrderRequest:{}",
                                    updateWithdrawalOrderRequest);
                                // 修改账务状态
                                boolean updateByTradeNo = updateByTradeNo(
                                    updateWithdrawalOrderRequest);
                                if (updateByTradeNo) {
                                    PAY_MONITOR.info("账务提现订单最终状态修改成功,订单号:{}", partner_trade_no);
                                    LOGGER.info("账务提现订单最终状态修改成功,订单号:{}", partner_trade_no);
                                } else {
                                    PAY_MONITOR.info("账务提现订单最终状态修改失败,订单号:{}", partner_trade_no);
                                    LOGGER.info("账务提现订单最终状态修改成功,订单号:{}", partner_trade_no);
                                }
                                returnCode = true;
                                return returnCode;

                            }
                        } else {
                            LOGGER.info("订单号为空");
                        }

                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    returnCode = false;
                    status.setRollbackOnly();
                }
                return returnCode;

            });

        } else {
            LOGGER.info("账务提现微信失败");
        }
    }

    public boolean updateByTradeNo(UpdateWithdrawalOrderRequest request) {
        TSingleResult<Boolean> result = userAccountWithdrawalService.updateByTradeNo(request);
        ServiceResultUtil.checkResult(result);
        return result.getValue();
    }

    public UserAccountWithdrawalDTO queryByTradeNo(String tradeNo) {
        TSingleResult<UserAccountWithdrawalDTO> result = userAccountWithdrawalService
            .queryByTradeNo(tradeNo);
        ServiceResultUtil.checkResult(result);
        return result.getValue();
    }

    @Override
    public void invokeTK(TransfersOrderWeixinRequest request, String punchRecordId) {
        TransfersOrderWeixinResult wresult = wechatManager.transferInvoke(request);

        // 消息发送成功
        if (wresult != null) {
            PAY_MONITOR.info("微信企业付款结果,wresult:{}", wresult);
            boolean result = transactionTemplate.execute(status -> {
                boolean returnCode = false;
                try {
                    String partner_trade_no = request.getPartner_trade_no();
                    UserAccountWithdrawalDTO dto = queryByTradeNo(partner_trade_no);
                    if (dto != null) {
                        Map<String, Object> extInfo = dto.getExtInfo();
                        if (StringUtil.isNotEmpty(punchRecordId)) {
                            String userId = dto.getUserId();
                            TaoKeRecord taoKeRecord = taoKeRecordMapper
                                .selectByPrimaryKey(Integer.parseInt(punchRecordId));
                            if (taoKeRecord != null) {
                                //已打卡
                                UpdateWithdrawalOrderRequest orderReq = new UpdateWithdrawalOrderRequest();
                                orderReq.setRefund(true);
                                orderReq.setTradeNo(partner_trade_no);
                                orderReq.genKey();
                                TaoKeRecord upRecord = new TaoKeRecord();
                                upRecord.setId(Integer.parseInt(punchRecordId));
                                if (WeixinXMLResult.SUCCESS.equals(wresult.getReturn_code())
                                    && WeixinXMLResult.SUCCESS.equals(wresult.getResult_code())) {
                                    LOGGER.info("微信提现成功,订单号:{}", partner_trade_no);
                                    PAY_MONITOR.info("微信提现成功,订单号:{}", partner_trade_no);
                                    orderReq.setOutTradeNo(wresult.getPayment_no());
                                    orderReq.setStatus(FINISH.getCode());
                                    upRecord.setStatus("4");
                                } else {
                                    String err_code = wresult.getErr_code();
                                    String err_code_des = wresult.getErr_code_des();
                                    if (StringUtil.isEmpty(err_code_des)) {
                                        LOGGER.info("提现失败退款err_code_des:{}", err_code_des);
                                        PAY_MONITOR.info("提现失败退款err_code_des:{}", err_code_des);
                                        orderReq.setFailureReason("提现失败退款:" + err_code);
                                    } else {
                                        LOGGER.info("提现失败退款err_code_des:{}", err_code_des);
                                        PAY_MONITOR.info("提现失败退款err_code_des:{}", err_code_des);
                                        orderReq.setFailureReason("提现失败退款:" + err_code_des);
                                    }

                                    Map<String, Object> extInfos = new HashMap<>();

                                    extInfos.put("errCode", err_code);

                                    orderReq.setExtInfo(extInfo);
                                    if (V2_ACCOUNT_SIMPLE_BAN.getCode().equals(err_code)
                                        || V2_ACCOUNT_SIMPLE_BAN.getDesc().equals(err_code)
                                        || AMOUNT_LIMIT.getCode().equals(err_code)) {
                                        orderReq.setStatus(ERROR.getCode());
                                        upRecord.setStatus("5");
                                    } else {
                                        orderReq.setStatus(WITHDRAWAL.getCode());
                                        upRecord.setStatus("6");
                                    }
                                    LOGGER.info("微信提现失败,订单号:{},errCode:{}", partner_trade_no,
                                        wresult.getErr_code());
                                    PAY_MONITOR.info("微信提现失败,订单号:{},errCode:{}", partner_trade_no,
                                        wresult.getErr_code());
                                }
                                upRecord.setGmtModified(new Date());
                                taoKeRecordMapper.updateByPrimaryKeySelective(upRecord);
                                LOGGER.info("orderReq:{}", orderReq);
                                PAY_MONITOR.info("orderReq:{}", orderReq);
                                // 修改账务状态
                                boolean updateByTradeNo = updateByTradeNo(orderReq);
                                if (updateByTradeNo) {
                                    LOGGER.info("账务提现订单最终状态修改成功,订单号:{}", partner_trade_no);
                                    PAY_MONITOR.info("账务提现订单最终状态修改成功,订单号:{}", partner_trade_no);
                                } else {
                                    LOGGER.info("账务提现订单最终状态修改失败,订单号:{}", partner_trade_no);
                                    PAY_MONITOR.info("账务提现订单最终状态修改失败,订单号:{}", partner_trade_no);
                                }
                                returnCode = true;
                                return returnCode;
                            }
                        } else {
                            LOGGER.info("订单号为空");
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    returnCode = false;
                    status.setRollbackOnly();
                }
                return returnCode;

            });

        } else {
            LOGGER.info("账务提现微信失败");
        }
    }

}
