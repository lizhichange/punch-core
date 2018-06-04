package com.fulihui.punch.api;

import com.fulihui.punch.dto.UserPunchStatisticsDTO;
import com.fulihui.punch.request.SituationPeriodDateRequest;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;

/**
 * @author lz
 */
public interface StatisticsService {
    /**
     * @param request
     * @return
     */
    TSingleResult<UserPunchStatisticsDTO> queryPeriodDate(SituationPeriodDateRequest request);

    /**
     * 根据查询打卡列表
     *
     * @param request
     * @return UserPunchStatisticsDTO
     */
    TPageResult<UserPunchStatisticsDTO> queryPage(SituationPeriodDateRequest request);
}
