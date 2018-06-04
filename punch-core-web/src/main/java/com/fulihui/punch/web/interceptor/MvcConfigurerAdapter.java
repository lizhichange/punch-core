package com.fulihui.punch.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * 标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
 * @author lz
 *
 */
@Configuration
public class MvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对来自api/** 这个链接来的请求进行拦截
        registry.addInterceptor(userInterceptor()).addPathPatterns("/api/**");

    }

    /**
     *   //Swagger ui Mapping
     * @param registry
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Swagger ui Mapping
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

    }

    @Bean
    public UserInterceptor userInterceptor() {
        return new UserInterceptor();
    }
}