package com.fulihui.punch.web.config;

import org.near.toolkit.model.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author LM
 * @date 2017/1/3
 */
@Configuration
@Setter
@Getter
public class AppConfig extends ToString {

    private static final long serialVersionUID = 5006112760637889038L;
    /**
     * 产品环境，使用{@code @Value("${product.env}")}方式获取
     */
    @Value("${product.env}")
    private String            productEnv;

    @Value("${wechat.oauthAesKey}")
    private String            oauthAesKey;

    @Value("${wechat.wechatMPAppid}")
    private String            wechatMPAppid;

    @Value("${wechat.oauthUrl}")
    private String            oauthUrl;

    @Value("${wechat.configCode}")
    private String            wechatConfigCode;

    @Value("${wechat.hostURL}")
    private String            webappHostURL;

    @Value(("${punch.startTime}"))
    private long              punchStartTime;

    @Value(("${punch.endTime}"))
    private long              punchEndTime;

    @Value(("${mock.auth:false}"))
    private boolean           mockAuth;

    @Value(("${mock.openId:null}"))
    private String            mockOpenId;

    @Value(("${mock.userId:null}"))
    private String            mockUserId;

    @Value(("${mock.punch:false}"))
    private boolean           mockPunch;

    @Value("${swagger.show:false}")
    private boolean           swaggerShow;

    @Value("${dubbo.registry.address}")
    private String            registryAddress;

}
