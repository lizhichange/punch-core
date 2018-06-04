package com.fulihui.punch.web.interceptor;

import static com.fulihui.punch.web.controller.IndexController.PUNCH_TOKEN_KEY;
import static com.fulihui.punch.web.interceptor.util.SessionContext.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fulihui.punch.web.config.AppConfig;
import com.fulihui.punch.web.integration.UserWechatServiceClient;
import com.fulihui.punch.web.interceptor.util.RequestAop;
import com.fulihui.punch.web.interceptor.util.SessionContext;
import com.fulihui.punch.web.interceptor.util.SessionData;
import com.fulihui.punch.web.interceptor.util.WeChatUser;
import com.fulihui.punch.web.config.Props;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.request.OAuthWeixinRequest;
import com.fulihui.weixinmp.sdk.crypto.AESCrypto;
import com.google.common.collect.Maps;

/**
 *
 * @author lz
 * 
 */
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    UserWechatServiceClient     userWechatServiceClient;

    @Autowired
    AppConfig                   appConfig;

    @Autowired
    Props                       props;

    @Autowired
    WeixinClient                weixinClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //        Boolean x = getBoolean(request, response);
        //        if (x != null) {
        //            return x;
        //        }

        HttpSession session = request.getSession();
        String referer = request.getHeader("referer");

        session.setAttribute("referer", referer);

        WeChatUser weChatUser = readUser(PUNCH_TOKEN_KEY, appConfig.getOauthAesKey(), request);
        if (weChatUser == null) {
            try {
                OAuthWeixinRequest oAuthWeixinRequest = new OAuthWeixinRequest();
                oAuthWeixinRequest.setAppid(props.getWechatAppid());
                oAuthWeixinRequest.setScope("snsapi_userinfo");
                oAuthWeixinRequest.setRedirect_uri(props.getWechatCallbackUrl());
                String redirect = weixinClient.assembleURL(oAuthWeixinRequest);
                LOGGER.info("预授权重定向url:{}", redirect);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                Map<String, Object> json = Maps.newHashMap();
                json.put("gotoURL", redirect);
                writer.print(JSON.toJSONString(json));
                response.setStatus(UNAUTHORIZED.value());
                return false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        session.setAttribute(USER_ID_KEY, weChatUser.getUserId());
        session.setAttribute(OPEN_ID_KEY, weChatUser.getOpenId());
        return true;
    }

    private Boolean getBoolean(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 对来自后台的请求统一进行日志处理
         */
        HttpSession session = request.getSession();
        RequestAop aop = aopLog(request);
        String referer = request.getHeader("referer");
        //是否mock登录
        boolean mockAuth = appConfig.isMockAuth();
        if (mockAuth) {
            session.setAttribute(USER_ID_KEY, appConfig.getMockUserId());
            session.setAttribute(OPEN_ID_KEY, appConfig.getMockOpenId());
            SessionData sessionData = new SessionData();
            sessionData.setOpenId(appConfig.getMockOpenId());
            sessionData.setUserId(appConfig.getMockUserId());
            writeSessionContext(sessionData);
            return true;

        }
        session.setAttribute("referer", referer);
        LOGGER.debug("referer:{},sessionId:{}", referer, session.getId());
        WeChatUser weChatUser = readUser(PUNCH_TOKEN_KEY, appConfig.getOauthAesKey(), request);
        if (weChatUser == null) {
            try {
                Map<String, Object> json = Maps.newHashMap();
                String gotoUrl = aop.getServerName() + aop.getContextPath() + "/get";
                LOGGER.info("gotoUrl:{}", gotoUrl);
                json.put("gotoURL", gotoUrl);
                json.put("host", appConfig.getOauthUrl());
                response.getWriter().print(JSON.toJSONString(json));
                response.setStatus(UNAUTHORIZED.value());
                return false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        session.setAttribute(USER_ID_KEY, weChatUser.getUserId());
        session.setAttribute(OPEN_ID_KEY, weChatUser.getOpenId());

        SessionData sessionData = new SessionData();
        sessionData.setOpenId(weChatUser.getOpenId());
        sessionData.setUserId(weChatUser.getUserId());
        writeSessionContext(sessionData);
        return null;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o,
                                Exception e) throws Exception {
        SessionContext.clearSessionContext();
    }

    private RequestAop aopLog(HttpServletRequest request) {

        RequestAop aop = new RequestAop();
        aop.setLocalName(request.getLocalName());
        aop.setMethod(request.getMethod());
        aop.setServerName(request.getServerName());
        aop.setQueryString(request.getQueryString());
        aop.setUri(request.getRequestURI());
        aop.setUrl(request.getRequestURL().toString());
        aop.setContextPath(request.getContextPath());
        LOGGER.debug("aop:{}", aop);
        return aop;
    }

    public static WeChatUser readUser(String cookieName, String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie c : cookies) {
            if (cookieName.equals(c.getName())) {
                String json = AESCrypto.decrypt(c.getValue(), key.getBytes(), null).get(0);
                return JSONObject.parseObject(json, WeChatUser.class);
            }
        }
        return null;
    }

}