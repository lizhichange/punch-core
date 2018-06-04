package com.fulihui.punch.util;

import java.util.Map;
import java.util.TreeMap;

import org.near.toolkit.security.codec.MD5Coder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 生成服务器签名算法
     */
    public static String genServiceSign(Map<String, Object> param, String key) {
        if (param == null || param.isEmpty()) {
            return null;
        }
        String paramStr = genParamStr(param);
        paramStr += "&APPKEY=" + key;
        logger.debug("sing str:{}", paramStr);
        return MD5Coder.md5Encode(paramStr).toUpperCase();
    }

    private static String genParamStr(Map<String, Object> param) {
        TreeMap<String, Object> sorted = new TreeMap<>(param);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : sorted.entrySet()) {
            // 值不为空才添加
            if (entry.getValue() == null || entry.getValue().toString().isEmpty()) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue().toString());
        }
        return sb.toString();
    }

}
