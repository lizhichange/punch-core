package com.fulihui.punch.web.manager.impl;

import static com.fulihui.punch.web.enums.CumulativeStatus.TOMORROW;

import java.util.Date;

import org.near.toolkit.common.StringUtil;
import org.near.toolkit.model.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulihui.punch.dto.PunchJackpotAmountDTO;
import com.fulihui.punch.request.PunchJackpotAmountRequest;
import com.fulihui.punch.web.controller.vo.CumulativeVO;
import com.fulihui.punch.web.integration.JackpotAmountServiceClient;
import com.fulihui.punch.web.integration.PunchSubsidyServiceClient;
import com.fulihui.punch.web.manager.JackpotAmountManager;

/**
 *
 * @author lz
 */
@Component
public class JackpotAmountManagerImpl implements JackpotAmountManager {

    @Autowired
    JackpotAmountServiceClient jackpotAmountServiceClient;

    @Autowired
    PunchSubsidyServiceClient  punchSubsidyServiceClient;

    @Override
    public CumulativeVO queryLatelyJackpotAmount() {

        PunchJackpotAmountDTO cumulative = jackpotAmountServiceClient.queryMaxPeriodDate();
        CumulativeVO vo = conv(cumulative, (querySubsidyAmount(cumulative.getPeriodDate())));
        vo.setStatus(TOMORROW.getCode());
        return vo;
    }

    Money querySubsidyAmount(Date date) {
        String amount = punchSubsidyServiceClient.queryRedisByDate(date);
        if (StringUtil.isNotBlank(amount)) {
            Money money = new Money();
            money.setCent(Long.parseLong(amount));
            return money;
        }
        return new Money(0);
    }

    @Override
    public CumulativeVO queryPeriodDate(Date periodDate) {
        PunchJackpotAmountRequest request = new PunchJackpotAmountRequest();
        request.setPeriodDate(periodDate);
        PunchJackpotAmountDTO dto = jackpotAmountServiceClient.cumulative(request);
        return conv(dto, querySubsidyAmount(periodDate));
    }

    private CumulativeVO conv(PunchJackpotAmountDTO dto, Money subsidyAmount) {
        CumulativeVO vo = new CumulativeVO();
        if (dto == null) {
            vo.setCumulativeNumber(0);
            vo.setCumulativeAmount(0);
            return vo;
        }
        long cent = subsidyAmount.getCent();
        Integer cumulativeAmount = Math.toIntExact(dto.getCumulativeAmount() + cent);
        Integer cumulativeNumber = dto.getCumulativeNumber() + subsidyAmount.getAmount().intValue();
        Money money = new Money();
        money.setCent(cumulativeAmount);
        vo.setCumulativeAmount(money.getAmount().intValue());
        vo.setCumulativeNumber(cumulativeNumber);
        return vo;
    }
}
