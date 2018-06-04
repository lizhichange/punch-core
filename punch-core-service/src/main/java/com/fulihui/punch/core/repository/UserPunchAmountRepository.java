package com.fulihui.punch.core.repository;

import java.util.Date;

import com.fulihui.punch.dto.UserPunchAmountDTO;

public interface UserPunchAmountRepository {

    boolean save(UserPunchAmountDTO dto);

    UserPunchAmountDTO querySumPayAmount(String userId);

    UserPunchAmountDTO query(String userId, Date periodDate);

    boolean update(UserPunchAmountDTO dto);
}
