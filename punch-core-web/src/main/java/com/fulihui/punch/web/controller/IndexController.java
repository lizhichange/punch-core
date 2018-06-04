package com.fulihui.punch.web.controller;

import static com.fulihui.punch.web.interceptor.util.SessionContext.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.fulihui.punch.web.config.AppConfig;
import com.fulihui.punch.web.integration.UserWechatServiceClient;
import com.fulihui.punch.web.interceptor.util.SessionData;
import com.fulihui.punch.web.interceptor.util.WeChatUser;
import com.fulihui.punch.web.manager.PayNotifyManager;
import com.fulihui.usercore.dto.WechatUserDTO;
import com.fulihui.weixinclient.result.WeixinXMLResult;
import com.fulihui.weixinclient.util.XMLUtil;
import com.fulihui.weixinmp.sdk.crypto.AESCrypto;

import io.swagger.annotations.ApiOperation;

/**
 * The type Index controller.
 * @author lizhi
 */
@Controller
public class IndexController {

    private static final Logger LOGGER          = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    AppConfig                   appConfig;

    @Autowired
    UserWechatServiceClient     userWechatServiceClient;
    public static final String  PUNCH_TOKEN_KEY = "PUNCH_TOKEN_KEY";

    /**
     * The Pay notify manager.
     */
    @Autowired
    PayNotifyManager            payNotifyManager;

    public final static String  REGEX_DOMAIN    = "(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)";

    @RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
    @ApiOperation(value = "微信预授权回调", notes = "微信预授权回调", response = String.class)
    public String get(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String op = request.getParameter("op");

        Object referer = session.getAttribute("referer");

        LOGGER.info("referer:{},sessionId:{}", referer, session.getId());

        if (isNotBlank(op) && referer != null) {

            StringBuffer requestURL = request.getRequestURL();
            String appId = appConfig.getWechatMPAppid();
            byte[] bytes = Base64.decodeBase64(op);
            String openId = AESCrypto.decrypt(StringUtils.newStringUtf8(bytes),
                appConfig.getOauthAesKey().getBytes(), null).get(0);
            WechatUserDTO dto = userWechatServiceClient.querySingleOpenId(openId, appId);
            if (dto != null) {
                Pattern p = Pattern.compile(REGEX_DOMAIN, Pattern.CASE_INSENSITIVE);
                Matcher matcher = p.matcher(requestURL);
                if (matcher.find()) {
                    WeChatUser user = new WeChatUser();
                    user.setUserId(dto.getUserId());
                    user.setOpenId(dto.getOpenId());
                    write(user, PUNCH_TOKEN_KEY, appConfig.getOauthAesKey(), matcher.group(),
                        response);
                    session.setAttribute(USER_ID_KEY, user.getUserId());
                    session.setAttribute(OPEN_ID_KEY, user.getOpenId());
                    SessionData sessionData = new SessionData();
                    sessionData.setOpenId(user.getOpenId());
                    sessionData.setUserId(user.getUserId());
                    writeSessionContext(sessionData);

                    LOGGER.info("Cookie已找到，获取的userId:{}", user.getUserId());
                }
            }
        }

        LOGGER.info("referer:{},op:{}", referer, op);
        assert referer != null;
        return "redirect:" + referer.toString();
    }

    /**
     * Wei xin pay notify.
     *
     * @param request  the request
     * @param response the response
     */
    @RequestMapping(value = "/weiXinPayNotify", method = { RequestMethod.GET, RequestMethod.POST })
    @ApiOperation(value = "微信支付成功回调", notes = "微信支付成功回调")
    public void weiXinPayNotify(HttpServletRequest request, HttpServletResponse response) {
        BufferedReader br = null;
        try {
            LOGGER.info("微信回调,开始");
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            LOGGER.info("微信回调,请求返回结果:{}", sb.toString());
            payNotifyManager.weiXinPayNotify(sb);
            WeixinXMLResult xmlResult = new WeixinXMLResult();
            xmlResult.setReturn_code(WeixinXMLResult.SUCCESS);
            xmlResult.setReturn_msg("OK");
            PrintWriter writer = response.getWriter();
            writer.write(XMLUtil.toXMLString(xmlResult));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
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
