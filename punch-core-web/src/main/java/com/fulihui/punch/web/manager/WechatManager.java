/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.web.manager;

import com.fulihui.weixinclient.result.AuthAccessTokenWeixinResult;
import com.fulihui.weixinclient.result.OAuthUserInfoWeixinResult;

/**
 * @author Willard Hu on 2017/11/1.
 */
public interface WechatManager {

    /**
     * 微信预授权回调参数 code 获取 access_token 和 openid
     * @param code code
     * @return {@link AuthAccessTokenWeixinResult}
     */
    AuthAccessTokenWeixinResult oauthAccessToken(String code);

    /**
     * 预授权登陆获取用户信息
     * @param accessToken accessToken
     * @param openId openId
     * @return {@link OAuthUserInfoWeixinResult}
     */
    OAuthUserInfoWeixinResult oauthUserInfo(String accessToken, String openId);

    /**
     * 获取 access_token
     */
    String takeAccessToken(String appId, String secret);

    /**
     * 获取 jsapi_ticket
     */
    String tackJsapiTicket(String appId, String secret);

}
