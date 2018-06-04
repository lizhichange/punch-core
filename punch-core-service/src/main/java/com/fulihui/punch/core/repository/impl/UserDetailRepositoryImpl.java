/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.core.repository.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.fulihui.punch.core.repository.UserDetailRepository;
import com.fulihui.punch.dal.dataobj.TUserDetail;
import com.fulihui.punch.dal.dataobj.TUserDetailExample;
import com.fulihui.punch.dal.mapper.TUserDetailMapper;
import com.fulihui.punch.dto.UserDto;

/**
 * @author Willard Hu on 2017/11/1.
 */
@Repository
public class UserDetailRepositoryImpl implements UserDetailRepository {

    @Autowired
    TUserDetailMapper tUserDetailMapper;

    @Override
    public void insert(TUserDetail record, String operator) {
        Date now = new Date();
        // emoji 处理
        record.setNickname(Base64.encodeBase64String(record.getNickname().getBytes()));
        record.setGmtCreate(now);
        record.setCreateBy(operator);
        record.setGmtModified(now);
        record.setModifiedBy(operator);
        tUserDetailMapper.insertSelective(record);
    }

    @Override
    public boolean update(TUserDetail record, String operator) {
        record.setGmtModified(new Date());
        record.setModifiedBy(operator);
        return tUserDetailMapper.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public UserDto queryByPK(String userId) {
        return conv(tUserDetailMapper.selectByPrimaryKey(userId));
    }

    @Override
    public List<UserDto> queryByUserIds(List<String> userIds) {
        TUserDetailExample example = new TUserDetailExample();
        example.createCriteria().andUserIdIn(userIds);
        return conv(tUserDetailMapper.selectByExample(example));
    }

    private UserDto conv(TUserDetail src) {
        if (src == null) {
            return null;
        }
        // emoji 处理
        UserDto target = new UserDto();
        target.setUserId(src.getUserId());
        target.setNickName(new String(Base64.decodeBase64(src.getNickname())));
        target.setName(src.getName());
        target.setGender(src.getGender());
        target.setIdCard(src.getIdCard());
        target.setMobileNo(src.getMobileNo());
        target.setBirthday(src.getBirthday());
        target.setCreateBy(src.getCreateBy());
        target.setGmtCreate(src.getGmtCreate());
        target.setModifiedBy(src.getModifiedBy());
        target.setHeadImg(src.getHeadImg());
        target.setGmtModified(src.getGmtModified());
        return target;
    }

    private List<UserDto> conv(List<TUserDetail> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(this::conv).collect(Collectors.toList());
    }
}
