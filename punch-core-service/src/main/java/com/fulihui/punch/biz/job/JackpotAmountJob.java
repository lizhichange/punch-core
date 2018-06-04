package com.fulihui.punch.biz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * The type Jackpot amount job.
 *
 * @author lz
 */
@Component
public class JackpotAmountJob implements SimpleJob {

    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER  = LoggerFactory.getLogger(JackpotAmountJob.class);

    private volatile boolean   running = false;

    @Autowired
    InitAmountData             initAmountData;

    @Override
    public void execute(ShardingContext shardingContext) {

        LOGGER.info("running:{}", running);
        if (running) {
            LOGGER.warn("JackpotAmountJob.running值没改掉");
            return;
        }
        running = true;
        initAmountData.init();
        running = false;
    }

}