package com.fulihui.punch.web.integration;

import java.util.Date;

import com.fulihui.punch.dto.UserPunchStatisticsDTO;

/**
 * @author lizhi
 */
public interface StatisticsServiceClient {

    UserPunchStatisticsDTO queryPeriodDate(Date periodDate);
}
