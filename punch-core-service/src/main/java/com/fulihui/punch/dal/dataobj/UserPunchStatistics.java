package com.fulihui.punch.dal.dataobj;

import java.io.Serializable;
import java.util.Date;

public class UserPunchStatistics implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -7885291420515712883L;

    private Integer id;

    private Long payAmount;

    private Long successNum;

    private Date gmtCreate;

    private Date gmtModified;

    private Date periodDate;

    private Long payOneAmount;

    private Long subsidyAmount;

    private Long totalAmount;

    private Long subsidySetupAmount;

    private Long failNum;

    private Long partakeNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Long successNum) {
        this.successNum = successNum;
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

    public Date getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Date periodDate) {
        this.periodDate = periodDate;
    }

    public Long getPayOneAmount() {
        return payOneAmount;
    }

    public void setPayOneAmount(Long payOneAmount) {
        this.payOneAmount = payOneAmount;
    }

    public Long getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(Long subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getSubsidySetupAmount() {
        return subsidySetupAmount;
    }

    public void setSubsidySetupAmount(Long subsidySetupAmount) {
        this.subsidySetupAmount = subsidySetupAmount;
    }

    public Long getFailNum() {
        return failNum;
    }

    public void setFailNum(Long failNum) {
        this.failNum = failNum;
    }

    public Long getPartakeNum() {
        return partakeNum;
    }

    public void setPartakeNum(Long partakeNum) {
        this.partakeNum = partakeNum;
    }

    
    
    
    
    
}