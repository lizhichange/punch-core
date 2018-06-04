package com.fulihui.punch.request;

import java.util.Date;

import org.near.servicesupport.request.PageRequest;

/**
 * @author lizhi
 */
public class PunchSubsidyInfoRequest extends PageRequest{
    
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -695865088075679747L;


    private String status;

    private Date asDate;
    
    /**
     * 开始时间 根据asDate查询
     */
    private Date   startDate;
    /**
     * 结束时间 根据asDate查询
     */
    private Date   endDate;
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
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    
    

}
