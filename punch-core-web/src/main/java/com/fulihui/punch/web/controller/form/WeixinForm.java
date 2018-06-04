package com.fulihui.punch.web.controller.form;

import org.near.toolkit.model.ToString;

public class WeixinForm extends ToString {

    /**
     * 
     */
    private static final long serialVersionUID = -699109029979339253L;
    /**
     *  签名时间戳
     */

    private String            timestamp;
    /**
     * 签名随机串
     */

    private String            nonceStr;
    /**
     * 订单详情扩展字符串
     */

    private String            packageStr;
    /**
     * 签名方式MD5
     */

    private String            signType;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

}
