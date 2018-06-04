package com.fulihui.punch.dal.dataobj;

import java.io.Serializable;
import java.util.Date;

public class TaoKeRecord implements Serializable {
    private Integer id;

    private Date bindTime;

    private Integer memberId;

    private String orderTkType;

    private Date registerTime;

    private String siteId;

    private String siteName;

    private String status;

    private String unionId;

    private Date buyTime;

    private Long tbTradeParentId;

    private Date gmtCreate;

    private Date gmtModified;

    private String mobile;

    private String adzoneId;

    private String adzoneName;

    private String memberNick;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOrderTkType() {
        return orderTkType;
    }

    public void setOrderTkType(String orderTkType) {
        this.orderTkType = orderTkType;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Long getTbTradeParentId() {
        return tbTradeParentId;
    }

    public void setTbTradeParentId(Long tbTradeParentId) {
        this.tbTradeParentId = tbTradeParentId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdzoneId() {
        return adzoneId;
    }

    public void setAdzoneId(String adzoneId) {
        this.adzoneId = adzoneId;
    }

    public String getAdzoneName() {
        return adzoneName;
    }

    public void setAdzoneName(String adzoneName) {
        this.adzoneName = adzoneName;
    }

    public String getMemberNick() {
        return memberNick;
    }

    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bindTime=").append(bindTime);
        sb.append(", memberId=").append(memberId);
        sb.append(", orderTkType=").append(orderTkType);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", siteId=").append(siteId);
        sb.append(", siteName=").append(siteName);
        sb.append(", status=").append(status);
        sb.append(", unionId=").append(unionId);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", tbTradeParentId=").append(tbTradeParentId);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", mobile=").append(mobile);
        sb.append(", adzoneId=").append(adzoneId);
        sb.append(", adzoneName=").append(adzoneName);
        sb.append(", memberNick=").append(memberNick);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}