package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

/**
 * @author lizhi
 */
public class WeixinJsapiData extends ToString {

    /**
     * 
     */
    private static final long serialVersionUID = -7536948014454952179L;
    /**
     * appId
     */
    private String            appId;
    /**
     * 票据
     */
    private String            jsApiTicket;
    /**
     * 签名时间戳
     */
    private String            timestamp;

    /**签名随机串 */
    private String            nonceStr;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getJsApiTicket() {
        return jsApiTicket;
    }

    public void setJsApiTicket(String jsApiTicket) {
        this.jsApiTicket = jsApiTicket;
    }

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

}
