package com.fulihui.punch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * The type Jedis operate.
 */
@Component
public class JedisOperate {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisOperate.class);

    @Autowired
    private JedisPool           jedisPool;

    /**
     * Sets .
     *
     * @param key   the key
     * @param value the value
     * @return the
     */
    public long setnx(String key, String value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            LOGGER.error("setnx，key:{},value:{},失败：{}", key, value, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Set string.
     *
     * @param key   the key
     * @param value the value
     * @return the string
     */
    public String set(String key, String value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.error("get，key:{},失败：{}", key, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }
    
    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            LOGGER.error("set，key:{},失败：{}", key, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Get string.
     *
     * @param key the key
     * @return the string
     */
    public String get(String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error("get，key:{},失败：{}", key, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Del long.
     *
     * @param key the key
     * @return the long
     */
    public long del(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            LOGGER.error("del，key:{},失败：{}", key, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Hget string.
     *
     * @param redisKey the redis key
     * @param mapKey   the map key
     * @return the string
     */
    public String hget(String redisKey, String mapKey) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hget(redisKey, mapKey);
        } catch (Exception e) {
            LOGGER.error("hget，key:{},value:{},失败：{}", redisKey, mapKey, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Hset long.
     *
     * @param redisKey the redis key
     * @param mapKey   the map key
     * @param value    the value
     * @return the long
     */
    public long hset(String redisKey, String mapKey, String value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hset(redisKey, mapKey, value);
        } catch (Exception e) {
            LOGGER.error("hset，redisKey:{},mapKey:{}, value:{},失败：{}", redisKey, mapKey, value, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Decr long.
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public long decr(String key, long value) {
        long result = -1;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.decrBy(key, value);
        } catch (Exception e) {
            LOGGER.error("decr，key:{},value:{},失败：{}", key, value, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Incr long.
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public long incr(String key, long value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.incrBy(key, value);
        } catch (Exception e) {
            LOGGER.error("incr，key:{},value:{},失败：{}", key, value, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Expire long.
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public long expire(String key, int value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.expire(key, value);
        } catch (Exception e) {
            LOGGER.error("expire，key:{},value:{},失败：{}", key, value, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * Append long.
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public long append(String key, String value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.append(key, value);
        } catch (Exception e) {
            LOGGER.error("append，key:{},value:{},失败：{}", key, value, e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return result;
    }
}
