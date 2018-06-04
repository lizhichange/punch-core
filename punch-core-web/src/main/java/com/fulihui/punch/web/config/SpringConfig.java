package com.fulihui.punch.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.WeixinClientImpl;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * 管理 Spring Bean 实例
 *
 * @author Willard.Hu on 2016/11/4 0004.
 */
@Configuration
public class SpringConfig {

    @Autowired
    Environment env;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(env.getProperty("redis.pool.config.maxidle", Integer.class, 8));
        poolConfig.setMaxTotal(env.getProperty("redis.pool.config.maxtotal", Integer.class, 8));
        return poolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        return new JedisPool(jedisPoolConfig,
            env.getProperty("redis.pool.host", Protocol.DEFAULT_HOST),
            env.getProperty("redis.pool.port", Integer.class, Protocol.DEFAULT_PORT),
            env.getProperty("redis.pool.timeout", Integer.class, Protocol.DEFAULT_TIMEOUT),
            env.getProperty("redis.pool.password", (String) null));
    }

    @Bean
    public WeixinClient weixinClient() {
        return new WeixinClientImpl();
    }

}
