/**
 * fulihui.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.fulihui.punch.web.config;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zhangyingjie
 * @version: v 0.1 2017年12月15日 18:00  zhangyingjie Exp $
 */
@Component
public class CuratorClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorClientConfig.class);

    @Autowired
    AppConfig                   appConfig;

    private CuratorFramework    curatorFramework;

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    @PostConstruct
    void init() {
        if (curatorFramework == null) {
            curatorFramework = CuratorFrameworkFactory.newClient(appConfig.getRegistryAddress(),
                5000, 5000, new ExponentialBackoffRetry(1000, 5));
            curatorFramework.start();

        }
    }

}
