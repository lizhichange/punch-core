package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.UserPunchAmount;
import org.apache.ibatis.annotations.Param;

public interface ExtUserPunchAmountMapper {

    int addAmountByPrimaryKey(@Param("userId") String userId, @Param("payAmount") Long payAmount,
                              @Param("makeAmount") Long makeAmount);

    UserPunchAmount querySumPayAmount(@Param("userId") String userId);
}