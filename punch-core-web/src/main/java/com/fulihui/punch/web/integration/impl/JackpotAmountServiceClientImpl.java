package com.fulihui.punch.web.integration.impl;

import static org.near.servicesupport.util.ServiceResultUtil.checkResult;

import org.near.servicesupport.result.TSingleResult;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.JackpotAmountService;
import com.fulihui.punch.dto.PunchJackpotAmountDTO;
import com.fulihui.punch.request.PunchJackpotAmountRequest;
import com.fulihui.punch.web.integration.JackpotAmountServiceClient;

/**
 * @author lizhi
 */
@Component
public class JackpotAmountServiceClientImpl implements JackpotAmountServiceClient {
    @Reference(version = "1.0.0")
    JackpotAmountService jackpotAmountService;

    @Override
    public PunchJackpotAmountDTO cumulative(PunchJackpotAmountRequest request) {
        TSingleResult<PunchJackpotAmountDTO> result = jackpotAmountService.cumulative(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public PunchJackpotAmountDTO queryMaxPeriodDate() {
        TSingleResult<PunchJackpotAmountDTO> result = jackpotAmountService.queryMaxPeriodDate();
        checkResult(result);
        return result.getValue();
    }
}
