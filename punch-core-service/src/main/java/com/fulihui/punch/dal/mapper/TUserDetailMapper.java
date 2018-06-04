package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.TUserDetail;
import com.fulihui.punch.dal.dataobj.TUserDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserDetailMapper {
    long countByExample(TUserDetailExample example);

    int deleteByExample(TUserDetailExample example);

    int deleteByPrimaryKey(String userId);

    int insert(TUserDetail record);

    int insertSelective(TUserDetail record);

    List<TUserDetail> selectByExample(TUserDetailExample example);

    TUserDetail selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") TUserDetail record, @Param("example") TUserDetailExample example);

    int updateByExample(@Param("record") TUserDetail record, @Param("example") TUserDetailExample example);

    int updateByPrimaryKeySelective(TUserDetail record);

    int updateByPrimaryKey(TUserDetail record);
}