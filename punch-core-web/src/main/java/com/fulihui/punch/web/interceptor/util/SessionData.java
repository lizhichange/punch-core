package com.fulihui.punch.web.interceptor.util;

import org.near.toolkit.model.ToString;

/**
 * 用户Session中存放的数据模型
 * @author Willard.Hu on 2016/8/23 0023.
 */
public class SessionData extends ToString {
    private static final long serialVersionUID = 2800453931546581580L;
    /**
     * 用户唯一标识
     */
    private String            userId;

    private String            openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
