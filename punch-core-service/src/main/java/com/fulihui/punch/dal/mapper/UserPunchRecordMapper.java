package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.UserPunchRecord;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPunchRecordMapper {
    long countByExample(UserPunchRecordExample example);

    int deleteByExample(UserPunchRecordExample example);

    int insert(UserPunchRecord record);

    int insertSelective(UserPunchRecord record);

    List<UserPunchRecord> selectByExample(UserPunchRecordExample example);

    int updateByExampleSelective(@Param("record") UserPunchRecord record, @Param("example") UserPunchRecordExample example);

    int updateByExample(@Param("record") UserPunchRecord record, @Param("example") UserPunchRecordExample example);
}