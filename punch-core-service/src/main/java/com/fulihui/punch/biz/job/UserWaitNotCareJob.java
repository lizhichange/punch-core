package com.fulihui.punch.biz.job;

import static com.fulihui.punch.enums.OrderStatusEnum.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.core.repository.UserPunchStatisticsRepository;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample;
import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.mapper.ExtUserPunchRecordMapper;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;
import com.google.common.collect.Lists;

/**
 * @author lz
 */
@Component
public class UserWaitNotCareJob implements SimpleJob {

    private final static Logger   LOGGER = LoggerFactory.getLogger(UserWaitNotCareJob.class);

    @Autowired
    ExtUserPunchRecordMapper      extUserPunchRecordMapper;

    @Autowired
    UserPunchRepository           userPunchRepository;

    ExecutorService               executorService;

    @Autowired
    UserPunchStatisticsRepository userPunchStatisticsRepository;

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            Date now = new Date();
            String webFormat = DateUtils.formatWebFormat(now);
            Date periodDate = DateUtils.parseWebFormat(webFormat);
            LOGGER.info("当前时间:{},每天期号时间:{}", DateUtils.formatNewFormat(now), periodDate);
            try {
                boolean b = extUserPunchRecordMapper.updateNotCare(WAIT_PAY_SUCCESS.getCode(),
                    WAIT_NOT_CARE.getCode(), periodDate);
                if (!b) {
                    LOGGER.info("UserWaitNotCareJob,全部用户打卡完成");
                }
            } catch (Exception e) {
                LOGGER.error("UserWaitNotCareJob,修改未完成打卡用户为[打卡失败]异常");
            }
            LOGGER.info("每天10点01秒将已经成功支付未打卡的用户,状态置为未打卡状态,[status=打卡失败],执行成功");
            //统计打卡成功人数 和失败人数 参与人数
            //查询已经打卡数量
            executorService = new ScheduledThreadPoolExecutor(2, new BasicThreadFactory.Builder()
                .namingPattern("UserWaitNotCareJob-schedule-pool-%d").daemon(true).build());
            statistics(periodDate);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 统计打卡 成功 失败 参与人数
     * @param periodDate
     */
    private void statistics(Date periodDate) {
        //打卡成功人数
        UserPunchRecordExample example = new UserPunchRecordExample();
        //已完成,打卡成功
        List<String> arrayList = Lists.newArrayList(WAIT_ALREADY.getCode(), COMPLETED.getCode());
        example.createCriteria().andStatusIn(arrayList).andPeriodDateEqualTo(periodDate);
        long success = userPunchRepository.count(example);
        //打卡失败人数
        example = new UserPunchRecordExample();
        example.createCriteria().andStatusEqualTo(WAIT_NOT_CARE.getCode())
            .andPeriodDateEqualTo(periodDate);
        long fail = userPunchRepository.count(example);
        long partakeNum = success + fail;
        LOGGER.info("统计打卡成功人数:{},失败人数:{},参数人数:{},", success, fail, partakeNum);
        UserPunchStatisticsDTO dto = userPunchStatisticsRepository.queryPeriodDate(periodDate);
        if (dto != null) {
            UserPunchStatistics record = new UserPunchStatistics();
            record.setFailNum(fail);
            record.setSuccessNum(success);
            record.setPartakeNum(partakeNum);
            record.setId(dto.getId());
            userPunchStatisticsRepository.update(record);
        } else {
            //容错处理
            UserPunchStatisticsDTO newDTO = new UserPunchStatisticsDTO();
            newDTO.setFailNum(fail);
            newDTO.setSuccessNum(success);
            newDTO.setPartakeNum(partakeNum);
            newDTO.setPeriodDate(periodDate);
            userPunchStatisticsRepository.save(newDTO);
        }
    }

    @PreDestroy
    void destroy() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

}
