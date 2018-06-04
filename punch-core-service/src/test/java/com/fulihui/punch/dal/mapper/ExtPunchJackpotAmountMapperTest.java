package com.fulihui.punch.dal.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.fulihui.punch.AbstractToStringSupport;
import com.fulihui.punch.dal.dataobj.PunchJackpotAmount;

@ContextConfiguration(locations = { "classpath:spring/test-datasource-context.xml" })

public class ExtPunchJackpotAmountMapperTest extends AbstractToStringSupport {

    @Autowired
    ExtPunchJackpotAmountMapper extPunchJackpotAmountMapper;

    @Test
    public void query() {

        List<PunchJackpotAmount> amounts = extPunchJackpotAmountMapper.queryMaxPeriodDate();

        LOGGER.info("r:{}", amounts);
    }

}
