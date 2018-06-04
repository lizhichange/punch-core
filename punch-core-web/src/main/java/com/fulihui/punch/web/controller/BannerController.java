package com.fulihui.punch.web.controller;

import static com.fulihui.welfarecentre.facade.enums.BannerLocalEnum.*;
import static org.near.webmvcsupport.view.JsonResultBuilder.succ;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.near.webmvcsupport.view.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fulihui.punch.web.controller.vo.AdvertVO;
import com.fulihui.punch.web.integration.BannerServiceClient;
import com.fulihui.welfarecentre.facade.dto.BannerDTO;
import com.fulihui.welfarecentre.facade.request.BannerMultiLocalRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/banner")
@Api("查询广告BannerH5接口")
public class BannerController {
    private final transient Logger LOGGER    = LoggerFactory.getLogger(getClass());
    @Autowired
    private BannerServiceClient    bannerServiceClient;

    ScheduledExecutorService       executorService;

    private Map<String, AdvertVO>  advertMap = Maps.newHashMap();

    @PreDestroy
    void destroy() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder()
            .namingPattern("BannerController.-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> {
            LOGGER.debug("开关刷新缓存开始");
            advertMap = getStringAdvertVOMap();
            LOGGER.debug("开关刷新缓存结束");
        }, 0, 60 * 10, TimeUnit.SECONDS);
    }

    private Map<String, AdvertVO> getStringAdvertVOMap() {
        BannerMultiLocalRequest request = new BannerMultiLocalRequest();
        List<String> queryList = Lists.newArrayList(LOCAL_HOME_YIYUAN.getCode(),
            LOCAL_PAY_SUCC.getCode(), LOCAL_PUSH_USER.getCode(), LOCAL_PUSH_SUCC.getCode(),
            LOCAL_PUSH_FAIL.getCode());
        request.setLocations(queryList);
        try {
            request.setShowModule(0);
            Map<String, List<BannerDTO>> resultMap = bannerServiceClient.queryMultiLocal(request);

            if (!isEmpty(resultMap)) {
                return Maps.transformValues(resultMap, input -> {
                    AdvertVO vo1 = new AdvertVO();
                    assert input != null;
                    BannerDTO bannerDTO1 = input.get(0);
                    vo1.setId(bannerDTO1.getId().toString());
                    vo1.setAdvertName(bannerDTO1.getTitle());
                    vo1.setAdvertImg(bannerDTO1.getImgUrl());
                    vo1.setAdvertUrl(bannerDTO1.getClickLink());
                    vo1.setAdvertType(bannerDTO1.getLocation());
                    return vo1;
                });
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Maps.newHashMap();
    }

    @RequestMapping(value = "/queryBanner", method = { RequestMethod.GET, RequestMethod.POST })
    @ApiOperation(value = "查询广告Banner", notes = "查询广告Banner")
    private JsonResult<Map<String, AdvertVO>> queryBanner() {
        if (isEmpty(advertMap)) {
            advertMap = getStringAdvertVOMap();
            return succ(advertMap);
        }
        return succ(advertMap);
    }

}
