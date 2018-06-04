package com.fulihui.punch.web.integration.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.StatisticsService;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;
import com.fulihui.punch.request.SituationPeriodDateRequest;
import com.fulihui.punch.web.integration.StatisticsServiceClient;
import org.near.servicesupport.result.TSingleResult;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.near.servicesupport.util.ServiceResultUtil.checkResult;
/**
 * @author lizhi
 */
@Component
public class StatisticsServiceClientImpl implements StatisticsServiceClient {

    @Reference(version = "1.0.0")
    StatisticsService statisticsService;

    @Override
    public UserPunchStatisticsDTO queryPeriodDate(Date periodDate) {
        SituationPeriodDateRequest request = new SituationPeriodDateRequest();
        request.setPeriodDate(periodDate);
        TSingleResult<UserPunchStatisticsDTO> result = statisticsService.queryPeriodDate(request);
        checkResult(result);
        return result.getValue();
    }
}
