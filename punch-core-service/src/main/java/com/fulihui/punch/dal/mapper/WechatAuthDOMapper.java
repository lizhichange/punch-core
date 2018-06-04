package com.fulihui.punch.dal.mapper;

import java.util.List;

import com.fulihui.punch.dal.dataobj.WechatAuthDO;
import com.fulihui.punch.dal.dataobj.WechatAuthDOExample;
import org.apache.ibatis.annotations.Param;




public interface WechatAuthDOMapper {
    long countByExample(WechatAuthDOExample example);

    int deleteByExample(WechatAuthDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WechatAuthDO record);

    int insertSelective(WechatAuthDO record);

    List<WechatAuthDO> selectByExample(WechatAuthDOExample example);

    WechatAuthDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WechatAuthDO record, @Param("example") WechatAuthDOExample example);

    int updateByExample(@Param("record") WechatAuthDO record, @Param("example") WechatAuthDOExample example);

    int updateByPrimaryKeySelective(WechatAuthDO record);

    int updateByPrimaryKey(WechatAuthDO record);
}