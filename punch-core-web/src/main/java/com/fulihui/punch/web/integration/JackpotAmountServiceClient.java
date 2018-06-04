package com.fulihui.punch.web.integration;

import com.fulihui.punch.dto.PunchJackpotAmountDTO;
import com.fulihui.punch.request.PunchJackpotAmountRequest;

public interface JackpotAmountServiceClient {

    /**
     * 查询奖池金额
     *
     * @param request the request
     * @return the cumulative result
     */
    PunchJackpotAmountDTO cumulative(PunchJackpotAmountRequest request);

    PunchJackpotAmountDTO queryMaxPeriodDate();

}
