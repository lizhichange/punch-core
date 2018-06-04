package com.fulihui.punch.web.integration.impl;

import java.util.Date;

import org.near.servicesupport.result.TSingleResult;
import org.near.servicesupport.util.ServiceResultUtil;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.PunchSubsidyService;
import com.fulihui.punch.web.integration.PunchSubsidyServiceClient;
/**
 * @author lizhi
 */
@Component
public class PunchSubsidyServiceClientImpl implements PunchSubsidyServiceClient {

    @Reference(version = "1.0.0")
    PunchSubsidyService punchSubsidyService;

    @Override
    public String queryRedisByDate(Date date) {
        TSingleResult<String> result = punchSubsidyService.queryRedisByDate(date);
        ServiceResultUtil.checkResult(result);
        return result.getValue();
    }
}
