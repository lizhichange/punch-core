package com.fulihui.punch.request;

import java.util.Date;

import org.near.servicesupport.request.PageRequest;

public class PunchSubsidyInfoCreateRequest extends PageRequest{
    
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -695865088075679747L;

    private Long subsidyAmount;

    private String status;

    private Date asDate;

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

    public Date getAsDate() {
        return asDate;
    }

    public void setAsDate(Date asDate) {
        this.asDate = asDate;
    }
    
    

}
