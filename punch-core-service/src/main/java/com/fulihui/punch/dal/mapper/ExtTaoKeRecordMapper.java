package com.fulihui.punch.dal.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fulihui.punch.dal.dataobj.TaoKeRecord;

public interface ExtTaoKeRecordMapper {

    List<TaoKeRecord> group(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                            @Param("offset") int offset, @Param("limit") int limit);

    int count(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}