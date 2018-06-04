package com.fulihui.punch.dal.dataobj;

import java.util.Date;

public class PunchSubsidyInfo {
    private Integer id;

    private Long subsidyAmount;

    private String status;

    private Date gmtCreate;

    private Date gmtModified;

    private Date asDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(Long subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getAsDate() {
        return asDate;
    }

    public void setAsDate(Date asDate) {
        this.asDate = asDate;
    }
}