/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.web.controller;

import static com.fulihui.punch.web.controller.IndexController.PUNCH_TOKEN_KEY;
import static com.fulihui.punch.web.controller.IndexController.REGEX_DOMAIN;
import static com.fulihui.punch.web.interceptor.util.SessionContext.OPEN_ID_KEY;
import static com.fulihui.punch.web.interceptor.util.SessionContext.USER_ID_KEY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.fulihui.punch.api.UserDetailService;
import com.fulihui.punch.dto.UserDto;
import com.fulihui.punch.request.UserWechatLoginRequest;
import com.fulihui.punch.web.config.AppConfig;
import com.fulihui.punch.web.config.Props;
import com.fulihui.punch.web.interceptor.util.WeChatUser;
import com.fulihui.punch.web.manager.WechatManager;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.result.AuthAccessTokenWeixinResult;
import com.fulihui.weixinclient.result.OAuthUserInfoWeixinResult;
import com.fulihui.weixinmp.sdk.crypto.AESCrypto;

/**
 * 微信预授权回调控制层
 * @author Willard Hu on 2017/11/1.
 */
@Controller
@RequestMapping("/wechat")
public class WechatCallbackController {
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    WechatManager                  wechatManager;

    @Autowired
    Props                          props;
    @Autowired
    AppConfig                      appConfig;
    @Autowired
    WeixinClient                   weixinClient;

    @Autowired
    UserDetailService              userDetailService;

    @GetMapping("/callback")
    String oauthCallback(@RequestParam("code") String code, HttpServletRequest request,
                         HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        logger.info("code:{}", code);
        AuthAccessTokenWeixinResult authAccessTokenRes = wechatManager.oauthAccessToken(code);
        if (!authAccessTokenRes.isSuccess()) {
            throw new RuntimeException(authAccessTokenRes.getErrmsg());
        }

        String openId = authAccessTokenRes.getOpenid();
        OAuthUserInfoWeixinResult oauthUserRes = wechatManager
            .oauthUserInfo(authAccessTokenRes.getAccess_token(), openId);
        if (!oauthUserRes.isSuccess()) {
            throw new RuntimeException(oauthUserRes.getErrmsg());
        }
        UserWechatLoginRequest userWechatLoginRequest = new UserWechatLoginRequest();
        userWechatLoginRequest.setOpenId(openId);
        userWechatLoginRequest.setAppid(props.getWechatAppid());
        userWechatLoginRequest.setNickName(oauthUserRes.getNickname());
        userWechatLoginRequest.setGender(oauthUserRes.getSex());
        userWechatLoginRequest.setUnionid(oauthUserRes.getUnionid());
        userWechatLoginRequest.setHeadImg(oauthUserRes.getHeadimgurl());
        UserDto dto = userDetailService.wechatLogin(userWechatLoginRequest);
        logger.info("user:{}", dto);
        Assert.notNull(dto, "user is  not null");

        Pattern p = Pattern.compile(REGEX_DOMAIN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(request.getRequestURL());
        if (matcher.find()) {
            WeChatUser user = new WeChatUser();
            user.setUserId(dto.getUserId());
            user.setOpenId(openId);
            write(user, PUNCH_TOKEN_KEY, appConfig.getOauthAesKey(), matcher.group(), response);
            session.setAttribute(USER_ID_KEY, user.getUserId());
            session.setAttribute(OPEN_ID_KEY, openId);
        }

        String encrypt = AESCrypto.encrypt(openId, appConfig.getOauthAesKey().getBytes(), null);
        openId = Base64.encodeBase64URLSafeString(encrypt.getBytes());

        //如果外部授权
        String gotoUrl = request.getParameter("gotoUrl");

        if (StringUtil.isNotBlank(gotoUrl)) {
            logger.info("gotoUrl:{}", gotoUrl);
            boolean contains = gotoUrl.contains("?");
            if (contains) {
                gotoUrl += "&taoOp=" + openId;
            } else {
                gotoUrl += "?taoOp=" + openId;
            }
            logger.info("预授权之后,关联用户信息成功,跳转前端地址,gotoUrl:{},openId:{}", gotoUrl, openId);
            return "redirect:" + gotoUrl;
        }

        String successURL = (String) session.getAttribute("referer");

        logger.info("预授权之后,关联用户信息成功,跳转前端地址,successURL:{},openId:{}", successURL, openId);
        boolean contains = successURL.contains("?");
        if (contains) {
            successURL += "&taoOp=" + openId;
        } else {
            successURL += "?taoOp=" + openId;
        }
        return "redirect:" + successURL;
    }

    public static void write(WeChatUser user, String cookieName, String key, String domain,
                             HttpServletResponse response) {
        String encryptJson = AESCrypto.encrypt(JSON.toJSONString(user), key.getBytes(), null);
        Cookie cookie = new Cookie(cookieName, encryptJson);
        cookie.setMaxAge(-1);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
