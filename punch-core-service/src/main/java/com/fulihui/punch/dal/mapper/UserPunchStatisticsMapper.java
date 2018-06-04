package com.fulihui.punch.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.dataobj.UserPunchStatisticsExample;

public interface UserPunchStatisticsMapper {
    int countByExample(UserPunchStatisticsExample example);

    int deleteByExample(UserPunchStatisticsExample example);

    @Delete({
        "delete from user_punch_statistics",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_punch_statistics (id, pay_amount, ",
        "success_num, gmt_create, ",
        "gmt_modified, period_date, ",
        "pay_one_amount, subsidy_amount, ",
        "total_amount, subsidy_setup_amount, ",
        "fail_num, partake_num)",
        "values (#{id,jdbcType=INTEGER}, #{payAmount,jdbcType=BIGINT}, ",
        "#{successNum,jdbcType=BIGINT}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{gmtModified,jdbcType=TIMESTAMP}, #{periodDate,jdbcType=DATE}, ",
        "#{payOneAmount,jdbcType=BIGINT}, #{subsidyAmount,jdbcType=BIGINT}, ",
        "#{totalAmount,jdbcType=BIGINT}, #{subsidySetupAmount,jdbcType=BIGINT}, ",
        "#{failNum,jdbcType=BIGINT}, #{partakeNum,jdbcType=BIGINT})"
    })
    int insert(UserPunchStatistics record);

    int insertSelective(UserPunchStatistics record);

    List<UserPunchStatistics> selectByExample(UserPunchStatisticsExample example);

    @Select({
        "select",
        "id, pay_amount, success_num, gmt_create, gmt_modified, period_date, pay_one_amount, ",
        "subsidy_amount, total_amount, subsidy_setup_amount, fail_num, partake_num",
        "from user_punch_statistics",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserPunchStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPunchStatistics record, @Param("example") UserPunchStatisticsExample example);

    int updateByExample(@Param("record") UserPunchStatistics record, @Param("example") UserPunchStatisticsExample example);

    int updateByPrimaryKeySelective(UserPunchStatistics record);

    @Update({
        "update user_punch_statistics",
        "set pay_amount = #{payAmount,jdbcType=BIGINT},",
          "success_num = #{successNum,jdbcType=BIGINT},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "period_date = #{periodDate,jdbcType=DATE},",
          "pay_one_amount = #{payOneAmount,jdbcType=BIGINT},",
          "subsidy_amount = #{subsidyAmount,jdbcType=BIGINT},",
          "total_amount = #{totalAmount,jdbcType=BIGINT},",
          "subsidy_setup_amount = #{subsidySetupAmount,jdbcType=BIGINT},",
          "fail_num = #{failNum,jdbcType=BIGINT},",
          "partake_num = #{partakeNum,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserPunchStatistics record);
}