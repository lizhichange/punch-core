package com.fulihui.punch.core.repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.near.toolkit.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.CollectionUtils;

import com.fulihui.punch.AbstractToStringSupport;
import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.dataobj.UserPunchStatisticsExample;
import com.fulihui.punch.dal.mapper.UserPunchStatisticsMapper;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderStatusEnum;

@ContextConfiguration(locations = { "classpath:spring/test-repository-context.xml",
                                    "classpath:spring/test-datasource-context.xml" })
public class UserPunchRepositoryTest extends AbstractToStringSupport {

    @Autowired
    UserPunchRepository       userPunchRepository;

    @Autowired
    UserPunchStatisticsMapper userPunchStatisticsMapper;

    @Test
    public void query() throws Exception {

        String asDate = DateUtils.format(new Date(), DateUtils.webFormat);
        try {
            Date periodDate = DateUtils.parseWebFormat(asDate);
            UserPunchStatisticsExample punchExample = new UserPunchStatisticsExample();
            punchExample.createCriteria().andPeriodDateEqualTo(periodDate);
            List<UserPunchStatistics> listStatistics = userPunchStatisticsMapper
                .selectByExample(punchExample);
            if (!CollectionUtils.isEmpty(listStatistics)) {
                UserPunchStatistics userPunchStatistics = listStatistics.get(0);
                if (userPunchStatistics == null) {
                    return;
                }
                long successNum = userPunchStatistics.getSuccessNum();
                if (successNum < 1) {
                    return;
                }

                int queryCount = userPunchRepository
                    .queryCount(OrderStatusEnum.WAIT_ALREADY.getCode(), periodDate);
                LOGGER.info("总条数,queryCount:{}", queryCount);
                if (queryCount > 0) {
                    int size = 100;
                    long page = queryCount / size;
                    LOGGER.info("总页数,page:{}", page);
                    Long payOneAmount = userPunchStatistics.getPayOneAmount();
                    if (payOneAmount == null) {
                        LOGGER.info("用户打款金额为空,无法打款");
                        return;
                    }
                    if (payOneAmount < 100) {
                        LOGGER.info("金额小于1元,无法打入用户余额中");
                        return;
                    }

                    for (int i = 1; i <= page + 1; i++) {

                        List<UserPunchRecordDTO> records = userPunchRepository.queryUserPunchStatus(
                            OrderStatusEnum.WAIT_ALREADY.getCode(), periodDate, i, size);
                        LOGGER.info("当前页面page:{},每页多少条数,size:{},数据条数:{},", i, size, records.size());

                    }
                }
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
