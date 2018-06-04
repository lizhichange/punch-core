/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.dto;

import com.fulihui.punch.enums.PrincipalTypeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Willard.Hu on 2017/10/31.
 */
@Getter
@Setter
public class WechatAuthDto extends BasicDto {
    private static final long serialVersionUID = -4745680379353896015L;

    /** 主键 */
    private Integer           id;
    /** 微信用户唯一标识 */
    private String            openId;
    /** 主体唯一标识 */
    private String            principalId;
    /** 主体类型 */
    private PrincipalTypeEnum principalType;
    /** 微信 appid */
    private String            appId;
    /** 开发者平台多微信下用户统一标识 */
    private String            unionid;
    /** 是否关注 */
    private Boolean           subscribe;
}
