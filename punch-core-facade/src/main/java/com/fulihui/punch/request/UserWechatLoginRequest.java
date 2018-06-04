/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.request;

import lombok.Getter;
import lombok.Setter;
import org.near.toolkit.model.ToString;

/**
 * @author Willard Hu on 2017/11/1.
 */
@Getter
@Setter
public class UserWechatLoginRequest extends ToString {
    private static final long serialVersionUID = -3124908964642528197L;

    /** 微信用户唯一标识 */
    private String            openId;
    /** 微信 appid */
    private String            appid;
    /** 昵称 */
    private String            nickName;
    /** 性别 */
    private String            gender;
    /** 开发者平台多微信下用户统一标识 */
    private String            unionid;
    /** 是否关注 */
    private Integer           subscribe;
    /**
     * 微信头像
     */
    private String            headImg;
}
