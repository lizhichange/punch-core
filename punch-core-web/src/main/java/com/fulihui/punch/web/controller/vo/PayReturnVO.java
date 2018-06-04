package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

/**
 * @author lizhi
 */
public class PayReturnVO extends ToString {

    private static final long serialVersionUID = -4913320545011572430L;
    /**
     * 订单id
     */

    private String orderId;

    /**
     * 订单支付金额
     */
    private String amount;

    /**
     * 微信单号
     */
    private String weixinPayNo;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }



    public String getWeixinPayNo() {
        return weixinPayNo;
    }

    public void setWeixinPayNo(String weixinPayNo) {
        this.weixinPayNo = weixinPayNo;
    }
    
    
}
