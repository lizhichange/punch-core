/**
 * fulihui.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.fulihui.punch.common.config;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @author: zhangyingjie
 * @version: v 0.1 2017年12月15日 18:00  zhangyingjie Exp $
 */
@Component
public class CuratorClientUtils {

    private static final Logger LOGGER            = LoggerFactory
        .getLogger(CuratorClientUtils.class);

    @Autowired
    AppConst                    appConst;

    public static final String  ROOT_LOCKS        = "/PUNCH/CORE/LOCKS";

    public static final String  PUNCH_TOKEN_LOCKS = "/PUNCH/TOKEN/LOCKS";
    private CuratorFramework    curatorFramework;

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    @PostConstruct
    void init() {
        if (curatorFramework == null) {
            curatorFramework = CuratorFrameworkFactory.newClient(appConst.getDubboRegistryAddress(),
                5000, 5000, new ExponentialBackoffRetry(1000, 5));
            curatorFramework.start();
            try {
                ArrayList<String> list = Lists.newArrayList(ROOT_LOCKS, PUNCH_TOKEN_LOCKS);
                for (String item : list) {
                    Stat stat = curatorFramework.checkExists().forPath(item);
                    if (stat == null) {
                        /**
                         * 新增,递归创建,CreateMode.PERSISTENT 创建持久化节点（默认），
                         */
                        String path = curatorFramework.create().creatingParentsIfNeeded()
                            .withMode(CreateMode.PERSISTENT).forPath(item, "123".getBytes());
                        if (StringUtil.isNotBlank(path)) {
                            LOGGER.info("path:{}", path);
                        }
                    }
                }

            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
