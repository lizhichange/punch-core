package com.fulihui.punch.web.integration;

import java.util.Date;

/**
 * The interface Punch subsidy service client.
 *
 * @author lizhi
 */
public interface PunchSubsidyServiceClient {

    /**
     * Query redis 补贴金额 根据时间
     *
     * @param date the date
     * @return the string
     */
    String queryRedisByDate(Date date);
}
