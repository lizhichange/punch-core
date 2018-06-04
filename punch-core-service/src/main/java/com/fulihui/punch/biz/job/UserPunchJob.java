package com.fulihui.punch.biz.job;

import java.text.ParseException;
import java.util.Date;

import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.punch.core.repository.UserPunchStatisticsRepository;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample.Criteria;
import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.mapper.UserPunchRecordMapper;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;
import com.fulihui.punch.enums.OrderStatusEnum;

/**
 * @author lz
 */
@Service
public class UserPunchJob implements SimpleJob {

    @Autowired
    UserPunchRecordMapper         userPunchRecordMapper;

    @Autowired
    UserPunchStatisticsRepository userPunchStatisticsRepository;

    private final static Logger   LOGGER = LoggerFactory.getLogger(UserPunchJob.class);

    @Override
    public void execute(ShardingContext arg0) {
        Date date = new Date();
        LOGGER.info("每天凌晨一点统计,支付成功总金额开始,", DateUtils.formatWebFormat(date));
        //每天一点统计前一天的支付成功总金额
        String asDate = DateUtils.format(date, DateUtils.webFormat);
        try {
            Date periodDate = DateUtils.parseWebFormat(asDate);
            //根据时间查询成功支付人数
            UserPunchRecordExample example = new UserPunchRecordExample();
            Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(OrderStatusEnum.WAIT_PAY_SUCCESS.getCode());
            criteria.andPeriodDateEqualTo(periodDate);
            long count = userPunchRecordMapper.countByExample(example);
            LOGGER.info("查询支付成功人数,count:{},活动期号时间asDate:{}", count, asDate);
            UserPunchStatisticsDTO queryAsDate = userPunchStatisticsRepository
                .queryPeriodDate(periodDate);
            if (queryAsDate != null) {
                UserPunchStatistics dto = new UserPunchStatistics();
                //期号时间
                dto.setId(queryAsDate.getId());
                dto.setPayAmount(count * 100);
                int update = userPunchStatisticsRepository.update(dto);
                if (update > 0) {
                    LOGGER.info("每天凌晨一点统计支付成功金额,数据落地成功");
                }
            } else {
                try {
                    UserPunchStatisticsDTO dto = new UserPunchStatisticsDTO();
                    dto.setPeriodDate(periodDate);
                    userPunchStatisticsRepository.save(dto);
                } catch (Exception e) {

                }
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
