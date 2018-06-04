package com.fulihui.punch.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fulihui.punch.dal.dataobj.WechatTokenDO;
import com.fulihui.punch.dal.dataobj.WechatTokenDOExample;



public interface WechatTokenDOMapper {
    long countByExample(WechatTokenDOExample example);

    int deleteByExample(WechatTokenDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WechatTokenDO record);

    int insertSelective(WechatTokenDO record);

    List<WechatTokenDO> selectByExample(WechatTokenDOExample example);

    WechatTokenDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WechatTokenDO record, @Param("example") WechatTokenDOExample example);

    int updateByExample(@Param("record") WechatTokenDO record, @Param("example") WechatTokenDOExample example);

    int updateByPrimaryKeySelective(WechatTokenDO record);

    int updateByPrimaryKey(WechatTokenDO record);
}