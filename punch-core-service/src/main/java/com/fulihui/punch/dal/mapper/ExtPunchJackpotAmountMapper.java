package com.fulihui.punch.dal.mapper;

import java.util.List;

import com.fulihui.punch.dal.dataobj.PunchJackpotAmount;

public interface ExtPunchJackpotAmountMapper {

    List<PunchJackpotAmount> queryMaxPeriodDate();
}