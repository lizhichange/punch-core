package com.fulihui.punch.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fulihui.punch.dal.dataobj.PunchSubsidyInfo;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfoExample;

public interface PunchSubsidyInfoMapper {
    int countByExample(PunchSubsidyInfoExample example);

    int deleteByExample(PunchSubsidyInfoExample example);

    @Delete({
        "delete from punch_subsidy_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into punch_subsidy_info (id, subsidy_amount, ",
        "status, gmt_create, ",
        "gmt_modified, as_date)",
        "values (#{id,jdbcType=INTEGER}, #{subsidyAmount,jdbcType=BIGINT}, ",
        "#{status,jdbcType=VARCHAR}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{gmtModified,jdbcType=TIMESTAMP}, #{asDate,jdbcType=DATE})"
    })
    int insert(PunchSubsidyInfo record);

    int insertSelective(PunchSubsidyInfo record);

    List<PunchSubsidyInfo> selectByExample(PunchSubsidyInfoExample example);

    @Select({
        "select",
        "id, subsidy_amount, status, gmt_create, gmt_modified, as_date",
        "from punch_subsidy_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    PunchSubsidyInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PunchSubsidyInfo record, @Param("example") PunchSubsidyInfoExample example);

    int updateByExample(@Param("record") PunchSubsidyInfo record, @Param("example") PunchSubsidyInfoExample example);

    int updateByPrimaryKeySelective(PunchSubsidyInfo record);

    @Update({
        "update punch_subsidy_info",
        "set subsidy_amount = #{subsidyAmount,jdbcType=BIGINT},",
          "status = #{status,jdbcType=VARCHAR},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "as_date = #{asDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PunchSubsidyInfo record);
    
    
    int updateStatusByPrimaryKey(PunchSubsidyInfo record);
}