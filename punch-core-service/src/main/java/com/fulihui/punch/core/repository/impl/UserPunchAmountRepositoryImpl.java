package com.fulihui.punch.core.repository.impl;

import static com.fulihui.punch.biz.conv.UserPunchAmountConv.*;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.fulihui.punch.core.repository.UserPunchAmountRepository;
import com.fulihui.punch.dal.dataobj.UserPunchAmount;
import com.fulihui.punch.dal.dataobj.UserPunchAmountExample;
import com.fulihui.punch.dal.mapper.ExtUserPunchAmountMapper;
import com.fulihui.punch.dal.mapper.UserPunchAmountMapper;
import com.fulihui.punch.dto.UserPunchAmountDTO;

@Repository
public class UserPunchAmountRepositoryImpl implements UserPunchAmountRepository {

    @Autowired
    private UserPunchAmountMapper    userPunchAmountMapper;

    @Autowired
    private ExtUserPunchAmountMapper extUserPunchAmountMapper;

    @Override
    public boolean save(UserPunchAmountDTO dto) {
        UserPunchAmount amount = new UserPunchAmount();
        BeanUtils.copyProperties(dto, amount);
        amount.setGmtCreate(new Date());
        amount.setGmtModified(new Date());
        return userPunchAmountMapper.insertSelective(amount) > 0;
    }

    @Override
    public UserPunchAmountDTO querySumPayAmount(String userId) {
        UserPunchAmount amount = extUserPunchAmountMapper.querySumPayAmount(userId);
        return amount == null ? null : toDTO(amount);
    }

    @Override
    public UserPunchAmountDTO query(String userId, Date periodDate) {
        UserPunchAmountExample example = new UserPunchAmountExample();
        example.createCriteria().andUserIdEqualTo(userId).andPeriodDateEqualTo(periodDate);
        List<UserPunchAmount> list = userPunchAmountMapper.selectByExample(example);
        if (isEmpty(list)) {
            return null;
        }
        return toList(list).get(0);
    }

    @Override
    public boolean update(UserPunchAmountDTO dto) {
        Assert.notNull(dto, "update dto is not null");
        UserPunchAmount amount = toDO(dto);
        amount.setGmtModified(new Date());
        return userPunchAmountMapper.updateByPrimaryKeySelective(amount) > 0;
    }

}
