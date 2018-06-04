package com.fulihui.punch.biz.processor.impl;

import static com.fulihui.accountcore.enums.UserWithdrawalStatusEnum.WITHDRAWAL;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

import org.near.toolkit.common.RandomCharset;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.fulihui.accountcore.enums.ProductType;
import com.fulihui.accountcore.request.useraccount.UpdateWithdrawalOrderRequest;
import com.fulihui.accountcore.request.useraccount.UserApplyWithdrawalRequest;
import com.fulihui.punch.biz.integration.UserAccountWithdrawalServiceClient;
import com.fulihui.punch.biz.processor.WechatManager;
import com.fulihui.punch.common.config.AppConst;
import com.fulihui.punch.core.repository.UserPunchAmountRepository;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.core.zbus.ZbusProducerHandle;
import com.fulihui.punch.dto.UserPunchAmountDTO;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderStatusEnum;
import com.fulihui.punch.request.WeixinAccountRequest;
import com.fulihui.punch.request.WeixinAmountRequest;
import com.fulihui.punch.util.WechatConfigFactory;
import com.fulihui.systemcore.dto.WechatConfig;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.model.CertInfo;
import com.fulihui.weixinclient.request.order.TransfersOrderWeixinRequest;
import com.fulihui.weixinclient.result.order.TransfersOrderWeixinResult;
import com.fulihui.weixinclient.util.CertUtil;
import com.fulihui.weixinclient.util.ClassFieldsUtil;
import com.fulihui.weixinclient.util.WeixinSign;

@Component
public class WechatManagerImpl implements WechatManager {
    private final transient Logger             LOG    = LoggerFactory.getLogger(getClass());
    @Autowired
    private WeixinClient                       weixinClient;
    @Autowired
    private WechatConfigFactory                wechatConfigFactory;

    @Autowired
    private ZbusProducerHandle                 zbusProducerHandle;

    private final static Logger                LOGGER = LoggerFactory
        .getLogger(WechatManagerImpl.class);
    @Autowired
    AppConst                                   appConst;

    @Autowired
    private UserAccountWithdrawalServiceClient userAccountWithdrawalServiceClient;

    @Autowired
    private UserPunchRepository                userPunchRepository;

    @Autowired
    UserPunchAmountRepository                  userPunchAmountRepository;

    @Autowired
    private TransactionTemplate                transactionTemplate;

    @Override
    public TransfersOrderWeixinRequest transferByMechant(String userId, String tradeNO,
                                                         String openId, int amount, String desc) {
        TransfersOrderWeixinRequest request;
        WechatConfig wechatConfig;
        String sign;
        try {
            wechatConfig = wechatConfigFactory.getYouFuLi();
            // 设置请求参数
            request = new TransfersOrderWeixinRequest();
            request.setMch_appid(wechatConfig.getAppID());
            request.setMchid(wechatConfig.getMchId());
            request.setDevice_info("");
            request.setNonce_str(RandomCharset.randomMixture(32));
            request.setPartner_trade_no(tradeNO);
            request.setOpenid(openId);
            request.setCheck_name("NO_CHECK");
            request.setRe_user_name("");
            request.setAmount(amount);
            request.setDesc(desc);
            InetAddress netAddress = InetAddress.getLocalHost();

            request.setSpbill_create_ip(netAddress.getHostAddress());
            sign = WeixinSign.genServiceSign(ClassFieldsUtil.obj2StrValMap(request),
                wechatConfig.getSignKey());
            request.setSign(sign);
            LOG.info("####transfer start####========>>>>request:{}", request);
            return request;
        } catch (UnknownHostException e) {
            LOG.error(e.getMessage(), e);
        }

        return null;

    }

    @Override
    public TransfersOrderWeixinResult transferInvoke(TransfersOrderWeixinRequest request) {

        try {
            LOG.info("证书加载");
            CertInfo certInfo;
            TransfersOrderWeixinResult result;
            // 设置证书信息
            certInfo = new CertInfo();
            WechatConfig wechatConfig = wechatConfigFactory.getYouFuLi();
            certInfo.setCertFile(wechatConfig.getCertFile());
            certInfo.setCertPwd(wechatConfig.getMchId());
            CertUtil.setCertInfo(certInfo);
            result = weixinClient.invokeService(request);
            LOG.info("####transfer finish####========>>>>result:{}", result);
            return result;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return null;

    }

    @Override
    public void withdrawalAmount(WeixinAccountRequest request) {
        // 账务系统创建订单
        String openId = request.getOpenId();
        String userId = request.getUserId();
        Long amount = request.getAmount();
        if (amount <= 0) {
            return;
        }
        LOGGER.info("账务系统创建订单,weixinAccountRequest:{}", request);
        String outOrderId = request.getOutOrderId();
        if (StringUtil.isNotEmpty(openId) || StringUtil.isNotEmpty(outOrderId)) {
            UserApplyWithdrawalRequest userApplyWithdrawalRequest = new UserApplyWithdrawalRequest();
            userApplyWithdrawalRequest.setUserId(userId);
            userApplyWithdrawalRequest.setProduct(request.getType());
            userApplyWithdrawalRequest.setReason(request.getDesc());

            userApplyWithdrawalRequest.setRecordType(ProductType.YIYUAN_PUNCH.getCode());
            userApplyWithdrawalRequest.setDeduct(false);
            userApplyWithdrawalRequest.setAmount(amount);
            userApplyWithdrawalRequest.genKey();
            userApplyWithdrawalRequest.setExtInfo(request.getExtInfo());
            String applyWithdrawal = userAccountWithdrawalServiceClient
                .applyWithdrawal(userApplyWithdrawalRequest);

            if (StringUtils.isNotEmpty(applyWithdrawal)) {

                TransfersOrderWeixinRequest weixinRequest = transferByMechant(userId,
                    applyWithdrawal, openId, amount.intValue(), request.getDesc());
                WeixinAmountRequest wRequest = new WeixinAmountRequest();
                wRequest.setRequest(weixinRequest);
                wRequest.setPunchRecord(request.getOutOrderId());
                // 将参与记录加入 消息队列 异步保存
                boolean sendToMns = zbusProducerHandle.commitToMQ(JSON.toJSONString(wRequest),
                    request.getType());
                // 消息发送成功
                UpdateWithdrawalOrderRequest updateWithdrawalOrderRequest = new UpdateWithdrawalOrderRequest();
                updateWithdrawalOrderRequest.setRefund(true);
                updateWithdrawalOrderRequest.setTradeNo(applyWithdrawal);
                updateWithdrawalOrderRequest.genKey();
                if (sendToMns) {

                    boolean result = transactionTemplate.execute(status -> {

                        boolean updateByTradeNo = false;
                        try {
                            Map<String, Object> extInfo = request.getExtInfo();
                            // 账务状态改为提现中
                            updateWithdrawalOrderRequest.setStatus(WITHDRAWAL.getCode());
                            // 修改账务状态
                            updateWithdrawalOrderRequest.setExtInfo(extInfo);
                            updateByTradeNo = userAccountWithdrawalServiceClient
                                .updateByTradeNo(updateWithdrawalOrderRequest);
                            if (extInfo != null) {
                                Object object = extInfo.get("time");
                                if (object == null) {
                                    UserPunchRecordDTO recordUpdateDto = new UserPunchRecordDTO();
                                    recordUpdateDto.setUserId(userId);
                                    recordUpdateDto.setPunchOrderId(request.getOutOrderId());
                                    recordUpdateDto.setGmtModified(new Date());
                                    recordUpdateDto.setStatus(OrderStatusEnum.COMPLETED.getCode());
                                    recordUpdateDto.setRebateAmount(amount.intValue());
                                    boolean b = userPunchRepository.updateStatus(recordUpdateDto);
                                    if (b) {
                                        LOGGER.info("修改用户打卡订单记录成功,[已打卡]到[已完成]状态,recordUpdateDto:{}",
                                            recordUpdateDto);
                                        UserPunchAmountDTO query = userPunchAmountRepository
                                            .query(userId, request.getPeriodDate());
                                        if (query != null) {
                                            //update  统计用户累计金额
                                            UserPunchAmountDTO updateAmount = new UserPunchAmountDTO();
                                            updateAmount.setPeriodDate(query.getPeriodDate());
                                            updateAmount.setUserId(query.getUserId());
                                            //用户累计返利发放金额
                                            updateAmount.setMakeAmount(amount.intValue());
                                            boolean update = userPunchAmountRepository
                                                .update(updateAmount);
                                            if (update) {
                                                LOGGER.info("修改用户补贴金额累计表成功,updateAmount{}",
                                                    updateAmount);
                                            }
                                        }
                                    }
                                }
                            }

                            if (updateByTradeNo) {
                                LOGGER.info("账务提现订单状态修改成功,userId:{},trandeNo:{}", userId,
                                    applyWithdrawal);
                            } else {
                                LOGGER.info("账务提现订单状态修改失败,userId:{},trandeNo:{}", userId,
                                    applyWithdrawal);
                            }

                        } catch (Exception e) {
                            LOGGER.error(e.getMessage());
                            updateByTradeNo = false;
                            status.setRollbackOnly();
                        }
                        return updateByTradeNo;
                    });

                } else {
                    LOGGER.info("消息发送失败,userId:{}", userId);
                }

            } else {
                LOGGER.info("账务创建订单失败,userId:{}", userId);
            }
        } else {
            LOGGER.info("openid为空或者订单id为空:{}", request.toString());
        }
    }

    @Override
    public void withdrawalAmountTk(WeixinAccountRequest request) {
        // 账务系统创建订单
        String openId = request.getOpenId();
        String userId = request.getUserId();
        Long amount = request.getAmount();
        if (amount <= 0) {
            return;
        }
        LOGGER.info("账务系统创建订单,weixinAccountRequest:{}", request);
        String outOrderId = request.getOutOrderId();
        if (StringUtil.isNotEmpty(openId) || StringUtil.isNotEmpty(outOrderId)) {
            UserApplyWithdrawalRequest userApplyWithdrawalRequest = new UserApplyWithdrawalRequest();
            userApplyWithdrawalRequest.setUserId(userId);
            userApplyWithdrawalRequest.setProduct(request.getType());
            userApplyWithdrawalRequest.setReason(request.getDesc());

            userApplyWithdrawalRequest.setRecordType("TAOBAOKE");
            userApplyWithdrawalRequest.setDeduct(false);
            userApplyWithdrawalRequest.setAmount(amount);
            userApplyWithdrawalRequest.genKey();
            userApplyWithdrawalRequest.setExtInfo(request.getExtInfo());
            String applyWithdrawal = userAccountWithdrawalServiceClient
                .applyWithdrawal(userApplyWithdrawalRequest);

            if (StringUtils.isNotEmpty(applyWithdrawal)) {

                TransfersOrderWeixinRequest weixinRequest = transferByMechant(userId,
                    applyWithdrawal, openId, amount.intValue(), request.getDesc());
                WeixinAmountRequest wRequest = new WeixinAmountRequest();
                wRequest.setRequest(weixinRequest);
                wRequest.setPunchRecord(request.getOutOrderId());
                // 将参与记录加入 消息队列 异步保存
                boolean sendToMns = zbusProducerHandle.commitToMQ(JSON.toJSONString(wRequest),
                    request.getType());
                // 消息发送成功
                UpdateWithdrawalOrderRequest updateWithdrawalOrderRequest = new UpdateWithdrawalOrderRequest();
                updateWithdrawalOrderRequest.setRefund(true);
                updateWithdrawalOrderRequest.setTradeNo(applyWithdrawal);
                updateWithdrawalOrderRequest.genKey();
                if (sendToMns) {

                    boolean result = transactionTemplate.execute(status -> {

                        boolean updateByTradeNo = false;
                        try {
                            // 账务状态改为提现中
                            updateWithdrawalOrderRequest.setStatus(WITHDRAWAL.getCode());
                            updateByTradeNo = userAccountWithdrawalServiceClient
                                .updateByTradeNo(updateWithdrawalOrderRequest);

                            if (updateByTradeNo) {
                                LOGGER.info("账务提现订单状态修改成功,userId:{},trandeNo:{}", userId,
                                    applyWithdrawal);
                            } else {
                                LOGGER.info("账务提现订单状态修改失败,userId:{},trandeNo:{}", userId,
                                    applyWithdrawal);
                            }

                        } catch (Exception e) {
                            LOGGER.error(e.getMessage());
                            updateByTradeNo = false;
                            status.setRollbackOnly();
                        }
                        return updateByTradeNo;
                    });

                } else {
                    LOGGER.info("消息发送失败,userId:{}", userId);
                }

            } else {
                LOGGER.info("账务创建订单失败,userId:{}", userId);
            }
        } else {
            LOGGER.info("openid为空或者订单id为空:{}", request.toString());
        }
    }

}
