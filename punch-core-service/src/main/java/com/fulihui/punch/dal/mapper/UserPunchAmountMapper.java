package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.UserPunchAmount;
import com.fulihui.punch.dal.dataobj.UserPunchAmountExample;
import com.fulihui.punch.dal.dataobj.UserPunchAmountKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPunchAmountMapper {
    long countByExample(UserPunchAmountExample example);

    int deleteByExample(UserPunchAmountExample example);

    int deleteByPrimaryKey(UserPunchAmountKey key);

    int insert(UserPunchAmount record);

    int insertSelective(UserPunchAmount record);

    List<UserPunchAmount> selectByExample(UserPunchAmountExample example);

    UserPunchAmount selectByPrimaryKey(UserPunchAmountKey key);

    int updateByExampleSelective(@Param("record") UserPunchAmount record, @Param("example") UserPunchAmountExample example);

    int updateByExample(@Param("record") UserPunchAmount record, @Param("example") UserPunchAmountExample example);

    int updateByPrimaryKeySelective(UserPunchAmount record);

    int updateByPrimaryKey(UserPunchAmount record);
}