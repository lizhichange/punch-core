package com.fulihui.punch.dal.mapper;

import com.fulihui.punch.dal.dataobj.TaoPrompt;
import com.fulihui.punch.dal.dataobj.TaoPromptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaoPromptMapper {
    long countByExample(TaoPromptExample example);

    int deleteByExample(TaoPromptExample example);

    int deleteByPrimaryKey(String code);

    int insert(TaoPrompt record);

    int insertSelective(TaoPrompt record);

    List<TaoPrompt> selectByExample(TaoPromptExample example);

    TaoPrompt selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") TaoPrompt record, @Param("example") TaoPromptExample example);

    int updateByExample(@Param("record") TaoPrompt record, @Param("example") TaoPromptExample example);

    int updateByPrimaryKeySelective(TaoPrompt record);

    int updateByPrimaryKey(TaoPrompt record);
}