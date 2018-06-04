package com.fulihui.punch.core.repository.impl;

import java.util.Date;
import java.util.List;

import com.fulihui.punch.core.repository.WechatTokenRepository;
import com.fulihui.punch.dal.dataobj.WechatTokenDO;
import com.fulihui.punch.dal.dataobj.WechatTokenDOExample;
import com.fulihui.punch.dal.mapper.WechatTokenDOMapper;
import com.fulihui.systemcore.enums.WechatTokenTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;


/**
 * @author Willard Hu on 2017/10/31.
 */
@Repository
public class WechatTokenRepositoryImpl implements WechatTokenRepository {
    @Autowired
    private WechatTokenDOMapper wechatTokenDOMapper;

    @Override
    public void insert(WechatTokenDO record, String operator) {
        Date now = new Date();
        record.setGmtCreate(now);
        record.setCreateBy(operator);
        record.setGmtModified(now);
        record.setModifiedBy(operator);
        wechatTokenDOMapper.insertSelective(record);
    }

    @Override
    public boolean update(WechatTokenDO record, String operator) {
        record.setGmtModified(new Date());
        record.setModifiedBy(operator);
        return wechatTokenDOMapper.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public WechatTokenDO queryByType(String appid, WechatTokenTypeEnum tokenType) {
        WechatTokenDOExample example = new WechatTokenDOExample();
        example.createCriteria().andAppidEqualTo(appid)
                .andTokenTypeEqualTo(tokenType.getCode());
        List<WechatTokenDO> list = wechatTokenDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
