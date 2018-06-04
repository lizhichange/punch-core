/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.web.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.PunchWechatTokenService;
import com.fulihui.punch.request.WechatTokenQueryRequest;
import com.fulihui.punch.web.manager.WechatManager;
import com.fulihui.punch.web.config.Props;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.request.AuthAccessTokenWeixinRequest;
import com.fulihui.weixinclient.request.OAuthUserInfoWeixinRequest;
import com.fulihui.weixinclient.result.AuthAccessTokenWeixinResult;
import com.fulihui.weixinclient.result.OAuthUserInfoWeixinResult;

/**
 * @author Willard Hu on 2017/11/1.
 */
@Component
public class WechatManagerImpl implements WechatManager {
    @Autowired
    private Props                   props;
    @Autowired
    private WeixinClient            weixinClient;
    @Reference(version = "1.0.0")
    private PunchWechatTokenService punchWechatTokenService;

    @Override
    public AuthAccessTokenWeixinResult oauthAccessToken(String code) {
        AuthAccessTokenWeixinRequest request = new AuthAccessTokenWeixinRequest();
        request.setAppid(props.getWechatAppid());
        request.setSecret(props.getWechatAppsecret());
        request.setCode(code);
        return weixinClient.invokeService(request);
    }

    @Override
    public OAuthUserInfoWeixinResult oauthUserInfo(String accessToken, String openId) {
        OAuthUserInfoWeixinRequest oAuthUserInfoWeixinRequest = new OAuthUserInfoWeixinRequest();
        oAuthUserInfoWeixinRequest.setAccess_token(accessToken);
        oAuthUserInfoWeixinRequest.setOpenid(openId);
        return weixinClient.invokeService(oAuthUserInfoWeixinRequest);
    }

    @Override
    public String takeAccessToken(String appId,String secret) {
        WechatTokenQueryRequest tokenQueryRequest = new WechatTokenQueryRequest();
        tokenQueryRequest.setAppid(appId);
        tokenQueryRequest.setAppsecret(secret);
        return punchWechatTokenService.takeAccessToken(tokenQueryRequest);
    }

    @Override
    public String tackJsapiTicket(String appId,String secret) {
        WechatTokenQueryRequest tokenQueryRequest = new WechatTokenQueryRequest();
        tokenQueryRequest.setAppid(appId);
        tokenQueryRequest.setAppsecret(secret);
        return punchWechatTokenService.takeJsapiTicket(tokenQueryRequest);
    }

}
