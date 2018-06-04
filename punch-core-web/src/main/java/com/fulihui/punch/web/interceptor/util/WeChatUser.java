package com.fulihui.punch.web.interceptor.util;

import org.near.toolkit.model.ToString;

/**
 * @author LZ
 *
 */
public class WeChatUser extends ToString {
    private static final long serialVersionUID = 3730086723321817883L;
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
