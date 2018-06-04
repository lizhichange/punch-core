package com.fulihui.punch.web.interceptor.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * Created by Willard on 2015/9/6.
 */
public class ThreadCache {

    private static ThreadLocal<ConcurrentMap<String, Object>> threadCache = new InheritableThreadLocal<>();

    static {
        init();
    }

    public static void init() {
        threadCache.set(new ConcurrentHashMap<>());
    }

    /**
     * 添加属性，如果已有则覆盖，并且返回旧值
     */
    public static Object setAttribute(String key, Object value) {
        if (threadCache.get() == null) {
            init();
        }
        return threadCache.get().put(key, value);
    }

    /**
     * 获取属性的值
     */
    public static Object getAttribute(String key) {
        if (threadCache.get() == null) {
            init();
        }
        return threadCache.get().get(key);
    }

    /**
     * 判断属性是否存在
     */
    public static boolean hasAttribute(String key) {
        return getAttribute(key) != null;
    }

    /**
     * 移除属性，并且返回被移除的值
     */
    public static Object removeAttribute(String key) {
        if (threadCache.get() == null) {
            init();
        }
        return threadCache.get().remove(key);
    }

    /**
     * 清除线程缓存
     */
    public static void clear() {
        threadCache.remove();
    }
}
