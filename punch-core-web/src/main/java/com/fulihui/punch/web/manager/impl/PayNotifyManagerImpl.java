package com.fulihui.punch.web.manager.impl;

import org.near.toolkit.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulihui.punch.dto.WechatAuthDto;
import com.fulihui.punch.web.integration.UserPunchServiceClient;
import com.fulihui.punch.web.integration.UserWechatServiceClient;
import com.fulihui.punch.web.manager.PayNotifyManager;
import com.fulihui.weixinclient.notifyparam.PayResultWeixinParam;
import com.fulihui.weixinclient.util.XMLUtil;

/**
 * @author lz
 */
@Component
public class PayNotifyManagerImpl implements PayNotifyManager {

    @Autowired
    UserPunchServiceClient userPunchServiceClient;
    
    @Autowired
    UserWechatServiceClient     userWechatServiceClient;

    @Override
    public void weiXinPayNotify(StringBuilder sb) {
        PayResultWeixinParam param = XMLUtil.parseObject(sb.toString(), PayResultWeixinParam.class);
        String orderId = param.getOut_trade_no();
        //更新订单状态
        if (StringUtil.equals(param.getReturn_code(), PayResultWeixinParam.SUCCESS)
            && StringUtil.equals(param.getResult_code(), PayResultWeixinParam.SUCCESS)) {
            //微信支付更改订单状态
            //支付成功
            String openId = param.getOpenid();
            WechatAuthDto dto = userWechatServiceClient.queryByOpenId(openId);
            if(dto != null){
                String userId = dto.getPrincipalId();
                if(StringUtil.isNotEmpty(userId)){
                    userPunchServiceClient.payNotifySuccess(userId, orderId);
                }
            }
            
        } else {
            //失败
            String errCode = param.getErr_code();
            String errCodeDes = param.getErr_code_des();
//            updateRequest.setStatus(OrderStatusEnum.WAIT_PAY_FAIL.getCode());
//            updateRequest.setPayDecs(errCode + errCodeDes);
        }

    }

}
