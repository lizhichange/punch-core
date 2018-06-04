/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.dto;

import java.util.Date;

import org.near.toolkit.model.ToString;



/**
 * @author Willard.Hu on 2017/9/29.
 */
public abstract class BasicDto extends ToString {
    private static final long serialVersionUID = 3163181664635333242L;

    /** 创建人 */
    protected String createBy;
    /** 创建时间 */
    protected Date   gmtCreate;
    /** 创建时间 Unix 时间戳 */
    protected Long   gmtCreateTime;
    /** 修改人 */
    protected String modifiedBy;
    /** 修改时间 */
    protected Date   gmtModified;
    /** 修改时间 Unix 时间戳 */
    protected Long   gmtModifiedTime;

    /* ========== Setter ========== */

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setGmtCreate(Date gmtCreate) {
        if (gmtCreate != null) {
            this.gmtCreate = gmtCreate;
            this.gmtCreateTime = gmtCreate.getTime() / 1000;
        } else {
            this.gmtCreate = null;
            this.gmtCreateTime = null;
        }
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setGmtModified(Date gmtModified) {
        if (gmtModified != null) {
            this.gmtModified = gmtModified;
            this.gmtModifiedTime = gmtModified.getTime() / 1000;
        } else {
            this.gmtModified = null;
            this.gmtModifiedTime = null;
        }
    }

    /* ========== Getter ========== */

    public String getCreateBy() {
        return createBy;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtCreateTime() {
        return gmtCreateTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public Long getGmtModifiedTime() {
        return gmtModifiedTime;
    }
}
