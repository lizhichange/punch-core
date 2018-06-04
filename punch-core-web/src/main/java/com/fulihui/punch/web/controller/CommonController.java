package com.fulihui.punch.web.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.near.webmvcsupport.view.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fulihui.punch.api.PunchWechatTokenService;
import com.fulihui.punch.request.WechatTokenQueryRequest;
import com.fulihui.punch.web.config.AppConfig;
import com.fulihui.punch.web.config.Props;
import com.fulihui.punch.web.config.WechatConfigFactory;
import com.fulihui.punch.web.controller.form.SignForm;
import com.fulihui.punch.web.integration.UserPunchServiceClient;
import com.fulihui.systemcore.api.WechatTokenService;
import com.fulihui.systemcore.dto.WechatConfig;
import com.fulihui.weixinclient.model.WeixinJsapiSign;
import com.fulihui.weixinclient.model.WeixinJsapiSignBuilder;

import io.swagger.annotations.Api;

/**
 * The type Common controller.
 * @author lizhi
 */
@RestController
@RequestMapping("/api/common")
@Api("微信公共请求接口")
public class CommonController {

    /**
     * The Wechat config factory.
     */
    @Autowired
    WechatConfigFactory         wechatConfigFactory;

    /**
     * The Wechat token service.
     */
    @Autowired
    WechatTokenService          wechatTokenService;

    @Autowired
    PunchWechatTokenService     punchWechatTokenService;

    /**
     * The User punch service client.
     */
    @Autowired
    UserPunchServiceClient      userPunchServiceClient;

    @Autowired
    Props                       props;

    @Autowired
    AppConfig                   appConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    public String takeAccessToken(String appid, String appsecret) {
        WechatTokenQueryRequest tokenQueryRequest = new WechatTokenQueryRequest();
        tokenQueryRequest.setAppid(appid);
        tokenQueryRequest.setAppsecret(appsecret);
        return punchWechatTokenService.takeAccessToken(tokenQueryRequest);
    }

    public String tackJsapiTicket(String appid, String appsecret) {
        WechatTokenQueryRequest tokenQueryRequest = new WechatTokenQueryRequest();
        tokenQueryRequest.setAppid(appid);
        tokenQueryRequest.setAppsecret(appsecret);
        return punchWechatTokenService.takeJsapiTicket(tokenQueryRequest);
    }

    /**
     * 微信获取jsapi的签名
     *
     * @param form the form
     * @return the json result
     */
    @RequestMapping(value = "/signData", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public JsonResult<WeixinJsapiSign> signData(@RequestBody SignForm form) {
        LOGGER.info("url: {}", form.getUrl());
        WechatConfig config = wechatConfigFactory.getWechatConfig(props.getConfigCode());
        String jsApiTicket = tackJsapiTicket(config.getAppID(), config.getAppsecret());
        LOGGER.info("jsapi: {}", jsApiTicket);
        JsonResult<WeixinJsapiSign> jsonResult = new JsonResult<>();
        WeixinJsapiSign weixinJsapiSign = WeixinJsapiSignBuilder.build(config.getAppID(),
            jsApiTicket, StringEscapeUtils.unescapeHtml4(form.getUrl()));
        LOGGER.info("weixinJsapiSign: {}", weixinJsapiSign.toString());
        jsonResult.setInfo(weixinJsapiSign);
        jsonResult.setSuccess(true);
        return jsonResult;
    }

}
