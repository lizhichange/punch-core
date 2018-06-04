package com.fulihui.punch.service;

import static org.near.servicesupport.result.ResultBuilder.succTSingle;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.text.ParseException;
import java.util.List;

import org.near.servicesupport.result.TSingleResult;
import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.JackpotAmountService;
import com.fulihui.punch.core.repository.PunchJackpotAmountRepository;
import com.fulihui.punch.dto.PunchJackpotAmountDTO;
import com.fulihui.punch.request.PunchJackpotAmountRequest;

@Service("jackpotAmountService")
public class JackpotAmountServiceImpl implements JackpotAmountService {
    private final static Logger  LOGGER = LoggerFactory.getLogger(JackpotAmountServiceImpl.class);

    @Autowired
    PunchJackpotAmountRepository punchJackpotAmountRepository;

    @Override
    public TSingleResult<PunchJackpotAmountDTO> queryMaxPeriodDate() {
        List<PunchJackpotAmountDTO> list = punchJackpotAmountRepository.queryMaxPeriodDate();
        if (isEmpty(list)) {
            return succTSingle(null);
        }
        return succTSingle(list.get(0));
    }

    @Override
    public TSingleResult<PunchJackpotAmountDTO> cumulative(PunchJackpotAmountRequest request) {
        String webFormat = DateUtils.formatWebFormat(request.getPeriodDate());
        try {
            PunchJackpotAmountDTO dto = punchJackpotAmountRepository
                .queryPeriodDate(DateUtils.parseWebFormat(webFormat));
            return succTSingle(dto);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return succTSingle(null);
    }

}
