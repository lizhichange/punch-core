package com.fulihui.punch.util;

import static org.near.servicesupport.request.RequestBuilder.buildT;
import static org.near.servicesupport.util.ServiceResultUtil.checkResult;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.near.servicesupport.result.TSingleResult;
import org.near.toolkit.common.EnumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fulihui.punch.common.config.AppConst;
import com.fulihui.systemcore.api.WechatConfigService;
import com.fulihui.systemcore.condition.WechatConfigCondition;
import com.fulihui.systemcore.dto.WechatConfig;
import com.fulihui.systemcore.enums.EnvTypeEnum;
import com.google.common.collect.Maps;

/**
 * 微信配置工厂
 * @author Created by Willard.Hu on 2016/6/2.
 */
@Component
public class WechatConfigFactory {
    private final transient Logger    LOG             = LoggerFactory
        .getLogger(WechatConfigFactory.class);

    @Autowired
    private WechatConfigService       wechatConfigService;
    @Autowired
    private AppConst                  appConst;

    private Map<String, WechatConfig> wechatConfigMap = Maps.newConcurrentMap();

    @PostConstruct
    public void init() {
        WechatConfig wechatConfig = query(appConst.getWechatConfigCode());
        if (wechatConfig != null) {
            wechatConfigMap.put(appConst.getWechatConfigCode(), wechatConfig);
        }
        wechatConfig = query(appConst.getPunchConfigCode ());
        if (wechatConfig != null) {
            wechatConfigMap.put(appConst.getPunchConfigCode(), wechatConfig);
        }
    }

    private WechatConfig query(String confCode) {
        WechatConfigCondition condition = new WechatConfigCondition();
        condition.setEnvType(EnumUtil.queryByCode(appConst.getProductEnv(), EnvTypeEnum.class));
        condition.setConfigCode(confCode);
        TSingleResult<WechatConfig> result = wechatConfigService.querySingle(buildT(condition));
        checkResult(result);
        WechatConfig wechatConfig = result.getValue();
        LOG.debug("获取的wechatConfig:{}", wechatConfig);
        return wechatConfig;
    }

    private WechatConfig getWechatConfig(String wechatCode) {
        return wechatConfigMap.get(wechatCode);
    }

    /**
     * 福礼惠配置信息
     * @return
     */
    public WechatConfig getWechat() {
        return getWechatConfig(appConst.getWechatConfigCode());
    }

    /**
     * 有福礼品配置信息
     * @return
     */
    public WechatConfig getYouFuLi() {
        return getWechatConfig(appConst.getPunchConfigCode());
    }

    @PreDestroy
    void destroy() {
        if (!CollectionUtils.isEmpty(wechatConfigMap)) {
            wechatConfigMap.clear();
        }
    }

}
