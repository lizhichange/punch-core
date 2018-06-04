package com.fulihui.punch.biz.job;

import static com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.accountcore.enums.ProductType;
import com.fulihui.punch.biz.processor.WechatManager;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.dataobj.UserPunchStatisticsExample;
import com.fulihui.punch.dal.mapper.UserPunchRecordMapper;
import com.fulihui.punch.dal.mapper.UserPunchStatisticsMapper;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderStatusEnum;
import com.fulihui.punch.request.WeixinAccountRequest;
import com.google.common.collect.Maps;

/**
 * @author lz
 *
 */
@Service
public class UserAmounJob implements SimpleJob {

    @Autowired
    private UserPunchStatisticsMapper userPunchStatisticsMapper;

    @Autowired
    private UserPunchRepository       userPunchRepository;

    private final static Logger       LOGGER  = LoggerFactory.getLogger(UserAmounJob.class);

    @Autowired
    private UserPunchRecordMapper     userPunchRecordMapper;

    @Autowired
    private WechatManager             wechatManager;

    private volatile boolean          running = false;

    @Override
    public void execute(ShardingContext arg0) {
        LOGGER.info("running:{}", running);
        if (running) {
            LOGGER.warn("UserNotifyMessageJob.running值没改掉");
            return;
        }
        running = true;

        String asDate = DateUtils.format(new Date(), DateUtils.webFormat);
        try {
            Date periodDate = DateUtils.parseWebFormat(asDate);
            UserPunchStatisticsExample punchExample = new UserPunchStatisticsExample();
            punchExample.createCriteria().andPeriodDateEqualTo(periodDate);
            List<UserPunchStatistics> listStatistics = userPunchStatisticsMapper
                .selectByExample(punchExample);
            if (isNotEmpty(listStatistics)) {
                UserPunchStatistics userPunchStatistics = listStatistics.get(0);
                if (userPunchStatistics == null) {
                    running = false;
                    return;
                }
                long successNum = userPunchStatistics.getSuccessNum();
                if (successNum < 1) {
                    running = false;
                    return;
                }

                int queryCount = userPunchRepository
                    .queryCount(OrderStatusEnum.WAIT_ALREADY.getCode(), periodDate);
                LOGGER.info("总条数,queryCount:{}", queryCount);
                if (queryCount > 0) {
                    Long payOneAmount = userPunchStatistics.getPayOneAmount();
                    if (payOneAmount == null) {
                        LOGGER.info("用户打款金额为空,无法打款");
                        running = false;
                        return;
                    }
                    if (payOneAmount < 100) {
                        LOGGER.info("金额小于1元,无法打入用户余额中");
                        running = false;
                        return;
                    }
                    // FIXME: 2018-2-2 待优化批量发送金额 ,前期数据量小,后期数据量大,采用分页发送

                    List<UserPunchRecordDTO> dtoList = userPunchRepository
                        .queryUserPunch(OrderStatusEnum.WAIT_ALREADY.getCode(), periodDate);
                    if (!CollectionUtils.isEmpty(dtoList)) {
                        LOGGER.info("records:{},periodDate:{}", dtoList.size(), periodDate);
                        dtoList.forEach(record -> {
                            WeixinAccountRequest request = new WeixinAccountRequest();
                            request.setAmount(payOneAmount);
                            request.setUserId(record.getUserId());
                            request.setOpenId(record.getOpenId());
                            request.setType(ProductType.YIYUAN_PUNCH.getCode());
                            request.setDesc(ProductType.YIYUAN_PUNCH.getDesc() + "申请");
                            request.setOutOrderId(record.getPunchOrderId());
                            request.setPeriodDate(record.getPeriodDate());
                            Map<String, Object> extInfo = Maps.newHashMap();
                            extInfo.put("punchRecord", record.getPunchOrderId());
                            request.setExtInfo(extInfo);
                            wechatManager.withdrawalAmount(request);
                        });
                    }

                }
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        running = false;
    }

}
