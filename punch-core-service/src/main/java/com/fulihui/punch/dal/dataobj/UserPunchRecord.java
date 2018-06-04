package com.fulihui.punch.dal.dataobj;

import java.io.Serializable;
import java.util.Date;

public class UserPunchRecord implements Serializable {
    private String punchOrderId;

    private String userId;

    private String outTradeNo;

    private Date punchDate;

    private String status;

    private Date payDate;

    private String payType;

    private String openId;

    private String openIdChannel;

    private Date gmtCreate;

    private Date gmtModified;

    private String payDecs;

    private Integer payAmount;

    private Date pushStartDate;

    private Date pushEndDate;

    private Date periodDate;

    private Date payExtDate;

    private Integer rebateAmount;

    private static final long serialVersionUID = 1L;

    public String getPunchOrderId() {
        return punchOrderId;
    }

    public void setPunchOrderId(String punchOrderId) {
        this.punchOrderId = punchOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Date getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(Date punchDate) {
        this.punchDate = punchDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenIdChannel() {
        return openIdChannel;
    }

    public void setOpenIdChannel(String openIdChannel) {
        this.openIdChannel = openIdChannel;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getPayDecs() {
        return payDecs;
    }

    public void setPayDecs(String payDecs) {
        this.payDecs = payDecs;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPushStartDate() {
        return pushStartDate;
    }

    public void setPushStartDate(Date pushStartDate) {
        this.pushStartDate = pushStartDate;
    }

    public Date getPushEndDate() {
        return pushEndDate;
    }

    public void setPushEndDate(Date pushEndDate) {
        this.pushEndDate = pushEndDate;
    }

    public Date getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Date periodDate) {
        this.periodDate = periodDate;
    }

    public Date getPayExtDate() {
        return payExtDate;
    }

    public void setPayExtDate(Date payExtDate) {
        this.payExtDate = payExtDate;
    }

    public Integer getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(Integer rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", punchOrderId=").append(punchOrderId);
        sb.append(", userId=").append(userId);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", punchDate=").append(punchDate);
        sb.append(", status=").append(status);
        sb.append(", payDate=").append(payDate);
        sb.append(", payType=").append(payType);
        sb.append(", openId=").append(openId);
        sb.append(", openIdChannel=").append(openIdChannel);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", payDecs=").append(payDecs);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", pushStartDate=").append(pushStartDate);
        sb.append(", pushEndDate=").append(pushEndDate);
        sb.append(", periodDate=").append(periodDate);
        sb.append(", payExtDate=").append(payExtDate);
        sb.append(", rebateAmount=").append(rebateAmount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}