package com.fulihui.punch.service;

import static org.near.servicesupport.error.Errors.Commons.REQUEST_PARAMETER_ERROR;
import static org.near.servicesupport.result.ResultBuilder.succTPage;
import static org.near.servicesupport.util.ServiceAssert.notNull;

import java.util.List;

import org.near.servicesupport.result.ResultBuilder;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.StatisticsService;
import com.fulihui.punch.core.repository.UserPunchStatisticsRepository;
import com.fulihui.punch.dal.dataobj.UserPunchStatisticsExample;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;
import com.fulihui.punch.request.SituationPeriodDateRequest;

/**
 * @author lizhi
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    UserPunchStatisticsRepository userPunchStatisticsRepository;

    @Override
    public TSingleResult<UserPunchStatisticsDTO> queryPeriodDate(SituationPeriodDateRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notNull(request.getPeriodDate(), REQUEST_PARAMETER_ERROR);
        UserPunchStatisticsDTO dto = userPunchStatisticsRepository
            .queryPeriodDate(request.getPeriodDate());
        return ResultBuilder.succTSingle(dto);
    }

    @Override
    public TPageResult<UserPunchStatisticsDTO> queryPage(SituationPeriodDateRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        UserPunchStatisticsExample example = toExample(request);
        List<UserPunchStatisticsDTO> query = userPunchStatisticsRepository.query(example);
        long count = userPunchStatisticsRepository.count(getCountExample(request));
        return succTPage(query, request.getPage(), request.getRows(), (int) count);
    }

    private UserPunchStatisticsExample getCountExample(SituationPeriodDateRequest request) {
        UserPunchStatisticsExample example = new UserPunchStatisticsExample();
        UserPunchStatisticsExample.Criteria criteria = example.createCriteria();
        if (null != request.getPeriodDate()) {
            criteria.andPeriodDateEqualTo(request.getPeriodDate());
        }
        if (null != request.getStartDate()) {
            criteria.andGmtCreateGreaterThanOrEqualTo(request.getStartDate());
        }

        if (null != request.getStopDate()) {
            criteria.andGmtCreateLessThanOrEqualTo(request.getStopDate());
        }

        return example;
    }

    private UserPunchStatisticsExample toExample(SituationPeriodDateRequest request) {
        UserPunchStatisticsExample example = getCountExample(request);
        int offset = request.getPage() > 1 ? (request.getPage() - 1) * request.getRows() : 0;
        example.setOffset(offset);
        example.setLimit(request.getRows());
        example.setOrderByClause(" period_date DESC");
        return example;
    }

}
