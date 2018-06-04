/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.api;


import com.fulihui.punch.dto.UserDto;
import com.fulihui.punch.dto.WechatAuthDto;
import com.fulihui.punch.request.UserWechatLoginRequest;

/**
 * The interface User detail service.
 *
 * @author Willard Hu on 2017/11/1.
 */
public interface UserDetailService {

    /**
     * 微信登录，已存在信息则直接返回用户信息，否则执行注册后返回用户信息
     *
     * @param request {@link UserWechatLoginRequest}
     * @return {@link UserDto}
     */
    UserDto wechatLogin(UserWechatLoginRequest request);

    /**
     * 用户唯一标识查询用户
     *
     * @param userId 用户唯一标识
     * @return {@link UserDto}
     */
    UserDto queryByUserId(String userId);

    /**
     * 用户唯一标识查询用户
     *
     * @param openId 用户openId
     * @return {@link UserDto}
     */
    WechatAuthDto queryByOpenId(String openId);
    
    
    /**
     * 用户唯一标识查询用户
     *
     * @param userId 用户openId
     * @return {@link UserDto}
     */
    WechatAuthDto queryWechatByUserId(String userId);
}
