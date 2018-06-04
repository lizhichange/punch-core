package com.fulihui.punch.biz.job;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.punch.common.config.AppConst;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.core.zbus.Const;
import com.fulihui.punch.core.zbus.ZbusProducerHandle;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderStatusEnum;
import com.fulihui.punch.enums.TemplateTypeEnum;
import com.fulihui.punch.request.MessageRequest;

/**
 * @author lz
 */
@Component
public class UserNotifyMessageJob implements SimpleJob {

    public static final Logger LOGGER  = LoggerFactory.getLogger(UserNotifyMessageJob.class);
    @Autowired
    UserPunchRepository        userPunchRepository;

    @Autowired
    ZbusProducerHandle         zbusProducerHandle;

    private volatile boolean   running = false;

    @Autowired
    AppConst                   appConst;

    @Override
    public void execute(ShardingContext shardingContext) {

        LOGGER.info("running:{}", running);
        if (running) {
            LOGGER.warn("UserNotifyMessageJob.running值没改掉");
            return;
        }
        running = true;

        Date now = new Date();
        String formatWebFormat = DateUtils.formatWebFormat(now);
        try {
            now = DateUtils.parseWebFormat(formatWebFormat);
            //查询成功支付的人数
            String status = OrderStatusEnum.WAIT_PAY_SUCCESS.getCode();
            long count;
            if (appConst.isMockTestRun()) {
                count = userPunchRepository.count(status);
            } else {
                count = userPunchRepository.count(status, now);
            }
            int size = 100;
            long page = count / size;

            LOGGER.info("UserNotifyMessageJob.总条数:{},总页数:{}", count, page);

            for (int i = 1; i <= page + 1; i++) {

                try {
                    Thread.sleep(RandomUtils.nextInt(1, 500));
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                List<UserPunchRecordDTO> list;
                if (appConst.isMockTestRun()) {
                    list = userPunchRepository.queryUserPunchStatus(status, i, size);
                } else {
                    list = userPunchRepository.queryUserPunch(status, now, i, size);
                }
                LOGGER.info("list:{}", list.size());

                if (isEmpty(list)) {
                    LOGGER.info("未查询到支付成功信息");
                    running = false;
                    return;
                }
                list.forEach(item -> {
                    try {
                        MessageRequest request = new MessageRequest();
                        request.setOpenId(item.getOpenId());
                        request.setChannel(TemplateTypeEnum.DAKA_TIXING.getCode());
                        request.setFirst("铃铃铃！开始打卡啦！\n" + "请在8:00到10:00前完成打卡，千万别错过咯。");
                        request.setKeyword1("福礼惠天天打卡");
                        request.setKeyword2("开始打卡");
                        request.setKeyword3(DateUtils.format(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
                        request.setRemark("\n ➜  马上去打卡");
                        zbusProducerHandle.commitToMQ(JSONObject.toJSONString(request),
                            Const.PUSH_NOTIFY_MSG);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                });
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        running = false;
    }
}
