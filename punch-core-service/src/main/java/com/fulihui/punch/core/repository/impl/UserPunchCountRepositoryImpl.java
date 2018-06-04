package com.fulihui.punch.core.repository.impl;

import static com.alibaba.dubbo.common.utils.CollectionUtils.isEmpty;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.fulihui.punch.core.repository.UserPunchCountRepository;
import com.fulihui.punch.dal.dataobj.UserPunchCount;
import com.fulihui.punch.dal.dataobj.UserPunchCountExample;
import com.fulihui.punch.dal.mapper.UserPunchCountMapper;
import com.fulihui.punch.dto.UserPunchCountDTO;

@Repository
public class UserPunchCountRepositoryImpl implements UserPunchCountRepository {

    @Autowired
    UserPunchCountMapper userPunchCountMapper;

    @Override
    public List<UserPunchCountDTO> query(UserPunchCountExample example) {

        return conv(userPunchCountMapper.selectByExample(example));

    }

    @Override
    public long count(UserPunchCountExample example) {

        return userPunchCountMapper.countByExample(example);
    }

    private UserPunchCountDTO conv(UserPunchCount src) {
        if (src == null) {
            return null;
        }
        UserPunchCountDTO target = new UserPunchCountDTO();
        BeanUtils.copyProperties(src, target);
        return target;
    }

    private List<UserPunchCountDTO> conv(List<UserPunchCount> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(this::conv).collect(Collectors.toList());
    }

    @Override
    public int save(UserPunchCountDTO dto) {
        UserPunchCount count = new UserPunchCount();
        BeanUtils.copyProperties(dto, count);
        count.setGmtCreate(new Date());
        count.setGmtModified(new Date());
        return userPunchCountMapper.insertSelective(count);
    }

    @Override
    public int update(UserPunchCountDTO dto) {
        UserPunchCount count = new UserPunchCount();
        BeanUtils.copyProperties(dto, count);
        count.setGmtModified(new Date());
        return userPunchCountMapper.updateByPrimaryKeySelective(count);

    }

    @Override
    public UserPunchCountDTO query(String userId) {
        UserPunchCountExample example = new UserPunchCountExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserPunchCount> list = userPunchCountMapper.selectByExample(example);
        if (isEmpty(list)) {
            return null;
        }
        return toDTO(list.get(0));
    }

    private UserPunchCountDTO toDTO(UserPunchCount count) {
        if (count == null) {
            return null;
        }
        UserPunchCountDTO dto = new UserPunchCountDTO();
        BeanUtils.copyProperties(count, dto);
        return dto;
    }
}
