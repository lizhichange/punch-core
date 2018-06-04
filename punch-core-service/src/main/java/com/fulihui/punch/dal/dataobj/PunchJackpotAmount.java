package com.fulihui.punch.dal.dataobj;

import java.io.Serializable;
import java.util.Date;

public class PunchJackpotAmount implements Serializable {
    private Integer id;

    private Integer cumulativeAmount;

    private Integer cumulativeNumber;

    private Date gmtCreate;

    private Date gmtModified;

    private Date periodDate;

    private Integer subsidyAmount;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCumulativeAmount() {
        return cumulativeAmount;
    }

    public void setCumulativeAmount(Integer cumulativeAmount) {
        this.cumulativeAmount = cumulativeAmount;
    }

    public Integer getCumulativeNumber() {
        return cumulativeNumber;
    }

    public void setCumulativeNumber(Integer cumulativeNumber) {
        this.cumulativeNumber = cumulativeNumber;
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

    public Integer getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(Integer subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cumulativeAmount=").append(cumulativeAmount);
        sb.append(", cumulativeNumber=").append(cumulativeNumber);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", periodDate=").append(periodDate);
        sb.append(", subsidyAmount=").append(subsidyAmount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}