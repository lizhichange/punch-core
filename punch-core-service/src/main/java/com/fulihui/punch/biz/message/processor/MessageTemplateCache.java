package com.fulihui.punch.biz.message.processor;


import com.fulihui.punch.dal.dataobj.PunchTemplateMsg;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LZ
 */
public class MessageTemplateCache {
    private static final Logger LOG = LoggerFactory
            .getLogger(MessageTemplateCache.class);

    /**
     * HTTP 方法的 API 请求路径，第一个KEY标识GET/POST方法
     */
    private static Map<String, PunchTemplateMsg> API_PATH = Maps.newHashMap();

    /**
     * 数据锁
     */
    private static ReentrantLock REFRESH_DATA_LOCK = new ReentrantLock();


    /**
     * 清除缓存
     */
    public static void clear() {
        try {
            REFRESH_DATA_LOCK.lock();
            API_PATH = Maps.newHashMap();
        } finally {
            REFRESH_DATA_LOCK.unlock();
        }
    }

    /**
     * 设置缓存
     *
     * @param map
     */
    public static void set(Map<String, PunchTemplateMsg> map) {
        try {
            REFRESH_DATA_LOCK.lock();
            API_PATH = map;
        } finally {
            REFRESH_DATA_LOCK.unlock();
        }
    }
    public static PunchTemplateMsg get(String key) {
        if (!CollectionUtils.isEmpty(API_PATH)) {
            return API_PATH.get(key);
        }
        return null;
    }
}
