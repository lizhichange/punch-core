package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.UserPunchCount;
import com.fulihui.punch.dal.dataobj.UserPunchCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPunchCountMapper {
    long countByExample(UserPunchCountExample example);

    int deleteByExample(UserPunchCountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPunchCount record);

    int insertSelective(UserPunchCount record);

    List<UserPunchCount> selectByExample(UserPunchCountExample example);

    UserPunchCount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPunchCount record, @Param("example") UserPunchCountExample example);

    int updateByExample(@Param("record") UserPunchCount record, @Param("example") UserPunchCountExample example);

    int updateByPrimaryKeySelective(UserPunchCount record);

    int updateByPrimaryKey(UserPunchCount record);
}