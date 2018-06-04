package com.fulihui.punch.dal.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.fulihui.punch.dal.dataobj.UserPunchRecord;

public interface ExtUserPunchRecordMapper {

    boolean updateStatus(@Param("record") UserPunchRecord record);

    boolean updateStatusByPunchOrderId(@Param("userId") String userId,
                                       @Param("punchOrderId") String punchOrderId,
                                       @Param("status") String status,
                                       @Param("punchDate") Date punchDate

    );

    boolean updateAfterPayed(@Param("record") UserPunchRecord record);

    boolean updateNotCare(@Param("fromStatus") String fromStatus,
                          @Param("toStatus") String toStatus, @Param("periodDate") Date periodDate);

    UserPunchRecord selectByPrimaryKey(@Param("punchOrderId") String punchOrderId,
                                       @Param("userId") String userId);
}