package com.fulihui.punch.biz.job;

import static com.fulihui.weixinclient.model.enums.WeiixnTradeStateEnum.*;
import static org.near.toolkit.common.RandomCharset.randomMixture;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.near.toolkit.common.DateUtils;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.punch.api.UserPunchService;
import com.fulihui.punch.common.config.AppConst;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample.Criteria;
import com.fulihui.punch.dal.mapper.ExtUserPunchRecordMapper;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderStatusEnum;
import com.fulihui.punch.request.WeChatPayNotifyRequest;
import com.fulihui.punch.util.ClassFieldsUtil;
import com.fulihui.punch.util.WechatConfigFactory;
import com.fulihui.systemcore.dto.WechatConfig;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.notifyparam.PayResultWeixinParam;
import com.fulihui.weixinclient.request.order.OrderQueryWeixinRequest;
import com.fulihui.weixinclient.result.order.OrderQueryWeixinResult;
import com.fulihui.weixinclient.util.WeixinSign;

/**
 * 微信订单状态检查
 *
 * @author willard
 * @date 16-2-7
 */
@Component
public class WeChatOrderStatusCheckJob implements SimpleJob {

    private final static Logger LOGGER  = LoggerFactory.getLogger(WeChatOrderStatusCheckJob.class);

    @Autowired
    private WechatConfigFactory wechatConfigFactory;

    @Autowired
    private WeixinClient        weixinClient;

    private volatile boolean    running = false;

    @Autowired
    UserPunchRepository         userPunchRepository;

    @Autowired
    ExtUserPunchRecordMapper    extUserPunchRecordMapper;

    @Autowired
    AppConst                    appConst;

    @Autowired
    private UserPunchService    userPunchService;

    @Override
    public void execute(ShardingContext shardingContext) {

        LOGGER.info("running:{}", running);
        if (running) {
            LOGGER.warn("JackpotAmountJob.running值没改掉");
            return;
        }
        running = true;
        //查询支付中的订单
        Date date = new Date();
        String asDate = DateUtils.format(date, DateUtils.webFormat);
        try {
            Date startDate = DateUtils.parseNewFormat(asDate + " 00:00:00");
            Date endDate = DateUtils.parseNewFormat(asDate + " 23:59:59");
            UserPunchRecordExample example = new UserPunchRecordExample();
            Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(OrderStatusEnum.WAIT_ING_PAY.getCode());
            criteria.andPayDateGreaterThanOrEqualTo(startDate);
            criteria.andPayDateLessThanOrEqualTo(endDate);
            example.setOrderByClause("pay_date DESC");
            int size = 100;
            int page = 1;
            int start = (page - 1) * size;
            example.setOffset(start);
            example.setLimit(size);
            List<UserPunchRecordDTO> recordList = userPunchRepository.query(example);
            LOGGER.info("支付查询:{}", recordList.size());
            if (CollectionUtils.isNotEmpty(recordList)) {
                for (UserPunchRecordDTO record : recordList) {
                    String punchOrderId = record.getPunchOrderId();
                    String outTradeNo = record.getOutTradeNo();
                    if (StringUtil.isEmpty(outTradeNo)) {
                        //处理下一条订单数据
                        continue;
                    }
                    OrderQueryWeixinResult result = weixinPayQuery(punchOrderId);
                    WeChatPayNotifyRequest request = new WeChatPayNotifyRequest();
                    request.setUserId(record.getUserId());
                    request.setPunchOrderId(punchOrderId);
                    if (StringUtil.equals(result.getReturn_code(), PayResultWeixinParam.SUCCESS)
                        && StringUtil.equals(result.getResult_code(),
                            PayResultWeixinParam.SUCCESS)) {
                        if (StringUtil.equals(result.getTrade_state(),
                            PayResultWeixinParam.SUCCESS)) {
                            //微信支付更改订单状态
                            userPunchService.payNotifySuccess(request);
                        } else if (StringUtil.equals(result.getTrade_state(), REFUND.getCode())
                                   || StringUtil.equals(result.getTrade_state(), CLOSED.getCode())
                                   || StringUtil.equals(result.getTrade_state(), REVOKED.getCode())
                                   || StringUtil.equals(result.getTrade_state(),
                                       PAYERROR.getCode())) {
                            request.setPayFailDesc(
                                result.getTrade_state() + result.getTrade_state_desc());
                            userPunchService.payNotifyFail(request);
                        }
                    }

                }
            }

        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        running = false;
    }

    public OrderQueryWeixinResult weixinPayQuery(String punchOrderId) {
        WechatConfig appConfig = wechatConfigFactory.getYouFuLi();
        OrderQueryWeixinRequest orderQueryReq = new OrderQueryWeixinRequest();
        orderQueryReq.setAppid(appConfig.getAppID());
        orderQueryReq.setMch_id(appConfig.getMchId());
        orderQueryReq.setOut_trade_no(punchOrderId);
        orderQueryReq.setNonce_str(randomMixture(32));
        String sign = WeixinSign.genServiceSign(ClassFieldsUtil.obj2StrValMap(orderQueryReq),
            appConfig.getSignKey());
        orderQueryReq.setSign(sign);
        LOGGER.info("#pay start query request:{}", orderQueryReq.toString());
        OrderQueryWeixinResult orderQueryRs = weixinClient.invokeService(orderQueryReq);
        LOGGER.info("#pay start query result:{}", orderQueryRs.toString());
        return orderQueryRs;

    }

}
