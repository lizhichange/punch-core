package com.fulihui.punch.api;

import com.fulihui.punch.request.PunchJackpotAmountRequest;
import org.near.servicesupport.result.TSingleResult;

import com.fulihui.punch.dto.PunchJackpotAmountDTO;

/**
 * @author lizhi
 */
public interface JackpotAmountService {

    TSingleResult<PunchJackpotAmountDTO> queryMaxPeriodDate();

    /**
     * 查询累计金额 参与人数
     *
     * @param request the request
     * @return CumulativeResult t single result
     */
    TSingleResult<PunchJackpotAmountDTO> cumulative(PunchJackpotAmountRequest request);
}
