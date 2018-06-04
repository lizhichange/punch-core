package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

/**
 * @author Administrator
 */
public class WeixinJSBridgeVO extends ToString{

    /**
     * 
     */
    private static final long serialVersionUID = -4589495337231561793L;
    
    private String appId;
    
    private String timeStamp;
    
    private String nonceStr;
    
    private String packageString;
    
    private String signType;
    
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageString() {
        return packageString;
    }

    public void setPackageString(String packageString) {
        this.packageString = packageString;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
    
    

}
