package com.fulihui.punch.common.config;

import org.near.toolkit.model.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LeeSon on 2016/12/25 0025.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AppConst extends ToString {
    private static final long serialVersionUID = -7773555776429464725L;
    /**
     *
     */
    @Value("${zbus.broker.address}")
    private String            zbusUrl;

    @Value("${product.env}")
    private String            productEnv;

    @Value("${wechat.configCode}")
    private String            wechatConfigCode;

    @Value("${dubbo.registry.address}")
    private String            dubboRegistryAddress;

    @Value("${mock.test.run}")
    private boolean           mockTestRun;

    @Value("${wechat.punch.configCode}")
    private String           punchConfigCode;

}