package com.fulihui.punch.biz.processor;

import com.fulihui.punch.request.WeixinAccountRequest;
import com.fulihui.weixinclient.request.order.TransfersOrderWeixinRequest;
import com.fulihui.weixinclient.result.order.TransfersOrderWeixinResult;

/**
 * The interface Wechat manager.
 * @author lizhi
 */
public interface WechatManager {

    /**
     * 企业微信转账(公众号->个人)
     * <p>
     * 业务场景：浙南提现
     *
     * @param userId  the user id
     * @param tradeNO 商户订单号
     * @param openId  会员openId
     * @param amount  金额(分)
     * @param desc    付款描述
     * @return transfers order weixin request
     */
    TransfersOrderWeixinRequest transferByMechant(String userId, String tradeNO, String openId,
                                                  int amount, String desc);

    /**
     * Transfer invoke transfers order weixin result.
     *
     * @param request the request
     * @return the transfers order weixin result
     */
    TransfersOrderWeixinResult transferInvoke(TransfersOrderWeixinRequest request);

    /**
     * Withdrawal amount.
     *
     * @param request the request
     */
    void withdrawalAmount(WeixinAccountRequest request);

    void withdrawalAmountTk(WeixinAccountRequest request);

}
