package com.fulihui.punch.web.integration;

import static org.near.toolkit.common.DateUtils.formatTimeFormat;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPunchServiceClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPunchServiceClientTest.class);

    @Test
    public void test01() {

        Date now = new Date();
        long timeFormat = Long.parseLong(formatTimeFormat(now));
        long checkTime = Long.parseLong("114000");
        LOGGER.info("当前时间时分秒,timeFormat:{},checkTime", timeFormat, checkTime);
        //如果当前时间大于不能支付
        if (timeFormat >= checkTime) {
            LOGGER.info("102", "系统结算中，请稍后再来参加挑战~");
        }
    }
}
