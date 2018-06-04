package com.fulihui.punch.core.repository;

import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.dataobj.UserPunchStatisticsExample;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;

import java.util.Date;
import java.util.List;

/**
 * @author LZ
 */
public interface UserPunchStatisticsRepository {

    int save(UserPunchStatisticsDTO dto);

    UserPunchStatisticsDTO queryPeriodDate(Date asDate);

    int update(UserPunchStatistics record);

    int count(UserPunchStatisticsExample example);

    List<UserPunchStatisticsDTO> query(UserPunchStatisticsExample example);
}
