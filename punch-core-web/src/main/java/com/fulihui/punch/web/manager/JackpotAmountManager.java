package com.fulihui.punch.web.manager;

import java.util.Date;

import com.fulihui.punch.web.controller.vo.CumulativeVO;

/**
 * The interface Jackpot amount manager.
 */
public interface JackpotAmountManager {

    /**
     * Query lately jackpot amount cumulative vo.
     *
     * @return the cumulative vo
     */
    CumulativeVO queryLatelyJackpotAmount();

    /**
     * Query period date cumulative vo.
     *
     * @param periodDate the period date
     * @return the cumulative vo
     */
    CumulativeVO queryPeriodDate(Date periodDate);
}
