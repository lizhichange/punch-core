package com.fulihui.punch.request;

import org.near.toolkit.model.ToString;

public class PunchSubsidyInfoUpdateRequest extends ToString{
    
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -695865088075679747L;

    private Long subsidyAmount;

    private String status;

    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
    

}
