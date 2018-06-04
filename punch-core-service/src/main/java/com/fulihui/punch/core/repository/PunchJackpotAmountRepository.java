package com.fulihui.punch.core.repository;

import java.util.Date;
import java.util.List;

import com.fulihui.punch.dto.PunchJackpotAmountDTO;

/**
 * @author lizhi
 */
public interface PunchJackpotAmountRepository {

    PunchJackpotAmountDTO queryPeriodDate(Date date);

    List<PunchJackpotAmountDTO> queryMaxPeriodDate();

    int save(PunchJackpotAmountDTO dto);

    int update(PunchJackpotAmountDTO dto);
}
