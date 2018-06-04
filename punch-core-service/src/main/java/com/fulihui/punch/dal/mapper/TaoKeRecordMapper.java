package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.TaoKeRecord;
import com.fulihui.punch.dal.dataobj.TaoKeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaoKeRecordMapper {
    long countByExample(TaoKeRecordExample example);

    int deleteByExample(TaoKeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaoKeRecord record);

    int insertSelective(TaoKeRecord record);

    List<TaoKeRecord> selectByExample(TaoKeRecordExample example);

    TaoKeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaoKeRecord record, @Param("example") TaoKeRecordExample example);

    int updateByExample(@Param("record") TaoKeRecord record, @Param("example") TaoKeRecordExample example);

    int updateByPrimaryKeySelective(TaoKeRecord record);

    int updateByPrimaryKey(TaoKeRecord record);
}