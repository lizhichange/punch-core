package com.fulihui.punch.web.config;

import static org.near.servicesupport.request.RequestBuilder.buildT;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.near.servicesupport.result.TSingleResult;
import org.near.servicesupport.util.ServiceResultUtil;
import org.near.toolkit.common.EnumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
    private AppConfig                 appConfig;

    @Autowired
    Props                             props;

    private Map<String, WechatConfig> wechatConfigMap = Maps.newConcurrentMap();

    @PostConstruct
    public void init() {

        String wechatCallbackUrl = props.getWechatCallbackUrl();


        WechatConfig wechatConfig = query(appConfig.getWechatConfigCode());
        if (wechatConfig != null) {
            wechatConfigMap.put(appConfig.getWechatConfigCode(), wechatConfig);
        }
        wechatConfig = query(props.getConfigCode());
        if (wechatConfig != null) {
            wechatConfigMap.put(props.getConfigCode(), wechatConfig);
        }
    }

    private WechatConfig query(String confCode) {
        WechatConfigCondition condition = new WechatConfigCondition();
        condition.setEnvType(EnumUtil.queryByCode(appConfig.getProductEnv(), EnvTypeEnum.class));
        condition.setConfigCode(confCode);
        TSingleResult<WechatConfig> result = wechatConfigService.querySingle(buildT(condition));
        ServiceResultUtil.checkResult(result);
        WechatConfig wechatConfig = result.getValue();
        LOG.debug("获取的wechatConfig:{}", wechatConfig);
        return wechatConfig;
    }

    public WechatConfig getWechatConfig(String wechatCode) {
        return wechatConfigMap.get(wechatCode);
    }

    @PreDestroy
    void destroy() {
        if (!CollectionUtils.isEmpty(wechatConfigMap)) {
            wechatConfigMap.clear();
        }
    }

}
