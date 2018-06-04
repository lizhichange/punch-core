package com.fulihui.punch.biz.processor.impl;

import static com.fulihui.weixinclient.util.ClassFieldsUtil.obj2StrValMap;
import static com.fulihui.weixinclient.util.WeixinSign.genServiceSign;
import static org.near.servicesupport.error.Errors.Commons.REQUEST_PARAMETER_ERROR;
import static org.near.servicesupport.util.ServiceAssert.notNull;
import static org.near.toolkit.common.RandomCharset.randomMixture;
import static org.near.toolkit.common.StringUtil.isBlank;

import java.util.Date;

import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulihui.punch.biz.processor.AbstractPaymentProcessor;
import com.fulihui.punch.common.config.AppConst;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderPayTypeEnum;
import com.fulihui.punch.request.UserPunchCreateRequest;
import com.fulihui.punch.util.LoggerNamesConstants;
import com.fulihui.punch.util.WechatConfigFactory;
import com.fulihui.systemcore.dto.WechatConfig;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.request.order.UnifiedOrderWeixinRequest;
import com.fulihui.weixinclient.result.WeixinXMLResult;
import com.fulihui.weixinclient.result.order.UnifiedOrderWeixinResult;

/**
 * 微信支付处理
 *
 * @author lz
  */
@Component
public class WeChatPaymentProcessor extends AbstractPaymentProcessor {

    private static final Logger LOGGER     = LoggerFactory.getLogger(WeChatPaymentProcessor.class);

    private static final Logger PAY_LOGGER = LoggerFactory
        .getLogger(LoggerNamesConstants.PAY_MONITOR);

    @Autowired
    UserPunchRepository         userPunchRepository;

    @Autowired
    WechatConfigFactory         wechatConfigFactory;

    @Autowired
    WeixinClient                weixinClient;

    @Autowired
    AppConst                    appConst;

    /**
     *
     * 打卡订单支付数据
     * @param dto
     */
    @Override
    public String payment(UserPunchRecordDTO dto, UserPunchCreateRequest request) {
        notNull(dto, REQUEST_PARAMETER_ERROR);

        String punchOrderId = userPunchRepository.create(dto);
        if (isBlank(punchOrderId)) {
            LOGGER.error("创建订单失败,模型数据:{}", dto);
            return null;
        }
        WechatConfig config = wechatConfigFactory.getYouFuLi();
        UnifiedOrderWeixinRequest orderRequest = new UnifiedOrderWeixinRequest();
        orderRequest.setAppid(config.getAppID());
        orderRequest.setMch_id(config.getMchId());
        orderRequest.setDevice_info("WEB");
        orderRequest.setNonce_str(randomMixture(32));
        orderRequest.setBody(request.getBodyString());
        orderRequest.setOut_trade_no(punchOrderId);
        orderRequest.setTotal_fee(dto.getPayAmount());
        orderRequest.setSpbill_create_ip(request.getHostAddress());
        orderRequest.setNotify_url(request.getNotifyURL());
        orderRequest.setTrade_type("JSAPI");
        orderRequest.setOpenid(dto.getOpenId());
        orderRequest.setSign(genServiceSign(obj2StrValMap(orderRequest), config.getSignKey()));

        LOGGER.info("支付预订单请求参数{}:", orderRequest);
        UnifiedOrderWeixinResult orderResult = weixinClient.invokeService(orderRequest);

        LOGGER.info("支付预订单结果返回{}:", orderResult);
        dto.setPunchOrderId(punchOrderId);

        if (orderResult == null
            || !StringUtil.equals(orderResult.getReturn_code(), WeixinXMLResult.SUCCESS)
            || !StringUtil.equals(orderResult.getResult_code(), WeixinXMLResult.SUCCESS)) {
            userPunchRepository.updateAfterPayed(dto);
            LOGGER.error("支付失败,orderId:{}", punchOrderId);
            return null;
        }

        dto.setPayType(getPayType().getCode());
        dto.setOutTradeNo(orderResult.getPrepay_id());
        dto.setPayDate(new Date());
        boolean bl = userPunchRepository.updateAfterPayed(dto);
        if (!bl) {
            LOGGER.info("支付预订单结果失败,修改订单信息失败,punchOrderId:{},outTradeNo:{},", punchOrderId,
                orderResult.getPrepay_id());
            return null;
        }
        LOGGER.info("支付预订单结果成功,punchOrderId:{},outTradeNo:{},", punchOrderId,
            orderResult.getPrepay_id());
        return orderResult.getPrepay_id();

    }

    @Override
    public OrderPayTypeEnum getPayType() {
        return OrderPayTypeEnum.WECHAT_PAY;
    }

}
