package com.fulihui.punch.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * Dubbo 相关配置项
 *
 * @author Willard.Hu on 2016/11/11 0011.
 */
@Configuration
public class DubboConfig {
    @Autowired
    Environment env;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName(env.getProperty("dubbo.application.name"));
        return application;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(env.getProperty("dubbo.registry.protocol"));
        registry.setAddress(env.getProperty("dubbo.registry.address"));
        registry.setTimeout(15000);
        return registry;
    }
}
