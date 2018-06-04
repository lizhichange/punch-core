package com.fulihui.punch.dto;

import java.util.Date;

import org.near.toolkit.model.ToString;

public class TaoPromptDTO extends ToString {
    private static final long serialVersionUID = 5588797923684701737L;
    private String code;

    private Date gmtCreate;

    private Date gmtModified;

    private String content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}