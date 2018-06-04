package com.fulihui.punch.web.controller;

import static com.fulihui.punch.web.controller.IndexController.PUNCH_TOKEN_KEY;
import static com.fulihui.punch.web.interceptor.UserInterceptor.readUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fulihui.punch.web.config.AppConfig;
import com.fulihui.punch.web.config.Props;
import com.fulihui.punch.web.interceptor.util.WeChatUser;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.request.OAuthWeixinRequest;
import com.fulihui.weixinmp.sdk.crypto.AESCrypto;

import io.swagger.annotations.Api;

/**
 * @author Administrator
 */
@RequestMapping("/auth")
@Api("外部授权")
@Controller
public class AuthApiController {
    private final transient Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    Props                          props;
    @Autowired
    WeixinClient                   weixinClient;

    @Autowired
    AppConfig                      appConfig;

    /**
     * 用于页面触发微信预授权
     */
    @RequestMapping("wxOauth")
    String wxOAuth(String wxConfigCode, String gotoUrl, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String referer = request.getHeader("referer");
        LOGGER.info("referer:{}", referer);

        WeChatUser weChatUser = readUser(PUNCH_TOKEN_KEY, appConfig.getOauthAesKey(), request);
        if (weChatUser == null) {
            LOGGER.info("gotoUrl:{}", gotoUrl);
            OAuthWeixinRequest oAuthWeixinRequest = new OAuthWeixinRequest();
            oAuthWeixinRequest.setAppid(props.getWechatAppid());
            oAuthWeixinRequest.setScope("snsapi_userinfo");

            String wechatCallbackUrl = props.getWechatCallbackUrl();
            if (StringUtil.isNotBlank(gotoUrl)) {
                boolean b = wechatCallbackUrl.contains("?");
                if (b) {
                    wechatCallbackUrl += "&gotoUrl=" + gotoUrl;

                } else {
                    wechatCallbackUrl += "?gotoUrl=" + gotoUrl;
                }
            }
            LOGGER.info("wechatCallbackUrl:{}", wechatCallbackUrl);
            oAuthWeixinRequest.setRedirect_uri(wechatCallbackUrl);
            String redirect = weixinClient.assembleURL(oAuthWeixinRequest);
            LOGGER.info("预授权重定向url:{}", redirect);
            return "redirect:" + redirect;
        }
        String openId = weChatUser.getOpenId();

        String encrypt = AESCrypto.encrypt(openId, appConfig.getOauthAesKey().getBytes(), null);
        openId = Base64.encodeBase64URLSafeString(encrypt.getBytes());

        boolean contains = gotoUrl.contains("?");
        if (contains) {
            gotoUrl += "&taoOp=" + openId;
        } else {
            gotoUrl += "?taoOp=" + openId;
        }
        LOGGER.info("预授权之后,关联用户信息成功,跳转前端地址,gotoUrl:{},openId:{}", gotoUrl, openId);
        return "redirect:" +gotoUrl;
    }
}
