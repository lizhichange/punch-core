package com.fulihui.punch.web.config;

import org.near.toolkit.model.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

/**
 * 配置映射类
 * @author Willard.Hu on 2017/10/30.
 */
@Getter
@Configuration
public class Props extends ToString {
    private static final long serialVersionUID = 7053780072722402387L;
    @Value("${props.aesKey}")
    private String            aesKey;
    @Value("${props.wechat.appid}")
    private String            wechatAppid;
    @Value("${props.wechat.appsecret}")
    private String            wechatAppsecret;
    @Value("${props.wechat.callbackUrl}")
    private String            wechatCallbackUrl;
    @Value("${props.wechat.configCode}")
    private String            configCode;


}
