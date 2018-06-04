package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.PunchJackpotAmount;
import com.fulihui.punch.dal.dataobj.PunchJackpotAmountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PunchJackpotAmountMapper {
    long countByExample(PunchJackpotAmountExample example);

    int deleteByExample(PunchJackpotAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PunchJackpotAmount record);

    int insertSelective(PunchJackpotAmount record);

    List<PunchJackpotAmount> selectByExample(PunchJackpotAmountExample example);

    PunchJackpotAmount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PunchJackpotAmount record, @Param("example") PunchJackpotAmountExample example);

    int updateByExample(@Param("record") PunchJackpotAmount record, @Param("example") PunchJackpotAmountExample example);

    int updateByPrimaryKeySelective(PunchJackpotAmount record);

    int updateByPrimaryKey(PunchJackpotAmount record);
}