package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.PunchTemplateMsg;
import com.fulihui.punch.dal.dataobj.PunchTemplateMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PunchTemplateMsgMapper {
    long countByExample(PunchTemplateMsgExample example);

    int deleteByExample(PunchTemplateMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PunchTemplateMsg record);

    int insertSelective(PunchTemplateMsg record);

    List<PunchTemplateMsg> selectByExample(PunchTemplateMsgExample example);

    PunchTemplateMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PunchTemplateMsg record, @Param("example") PunchTemplateMsgExample example);

    int updateByExample(@Param("record") PunchTemplateMsg record, @Param("example") PunchTemplateMsgExample example);

    int updateByPrimaryKeySelective(PunchTemplateMsg record);

    int updateByPrimaryKey(PunchTemplateMsg record);
}