package com.fulihui.punch.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhangyingjie
 * @version $Id: v 0.1 2016年12月27日 11:19 zhangyingjie Exp $
 */
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30 * 60)
@ImportResource({ "classpath:META-INF/spring/dubbo-consumer.xml" })
@ServletComponentScan
@EnableAutoConfiguration
public class SpringApplicationBootstrap {
    public static void main(String[] args) {
        //        PropertyConfigurator.configure("config/*");
        SpringApplication.run(SpringApplicationBootstrap.class, args);
    }
}