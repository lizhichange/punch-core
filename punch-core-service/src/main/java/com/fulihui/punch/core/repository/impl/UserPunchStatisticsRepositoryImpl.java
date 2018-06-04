package com.fulihui.punch.core.repository.impl;

import static com.alibaba.dubbo.common.utils.CollectionUtils.isEmpty;
import static com.fulihui.punch.util.Collections.emptyList;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fulihui.punch.core.repository.UserPunchStatisticsRepository;
import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.dataobj.UserPunchStatisticsExample;
import com.fulihui.punch.dal.mapper.UserPunchStatisticsMapper;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;

@Repository
public class UserPunchStatisticsRepositoryImpl implements UserPunchStatisticsRepository {
    @Autowired
    UserPunchStatisticsMapper userPunchStatisticsMapper;

    @Override
    public int save(UserPunchStatisticsDTO dto) {
        UserPunchStatistics record = new UserPunchStatistics();
        BeanUtils.copyProperties(dto, record);
        record.setGmtModified(new Date());
        record.setGmtCreate(new Date());
        return userPunchStatisticsMapper.insertSelective(record);

    }

    @Override
    public int count(UserPunchStatisticsExample example) {
        return userPunchStatisticsMapper.countByExample(example);
    }

    @Override
    public int update(UserPunchStatistics record) {
        record.setGmtModified(new Date());
        return userPunchStatisticsMapper.updateByPrimaryKeySelective(record);

    }

    @Override
    public UserPunchStatisticsDTO queryPeriodDate(Date asDate) {
        UserPunchStatisticsExample example = new UserPunchStatisticsExample();
        example.createCriteria().andPeriodDateEqualTo(asDate);
        List<UserPunchStatistics> list = userPunchStatisticsMapper.selectByExample(example);
        if (isEmpty(list)) {
            return null;
        }
        return toDTOList(list).get(0);
    }

    private UserPunchStatisticsDTO toDTO(UserPunchStatistics src) {
        if (src == null) {
            return null;
        }
        UserPunchStatisticsDTO target = new UserPunchStatisticsDTO();
        BeanUtils.copyProperties(src, target);
        return target;
    }

    private List<UserPunchStatisticsDTO> toDTOList(List<UserPunchStatistics> list) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserPunchStatisticsDTO> query(UserPunchStatisticsExample example) {
        List<UserPunchStatistics> list = userPunchStatisticsMapper.selectByExample(example);
        if (isEmpty(list)) {
            return emptyList();
        }
        return this.toDTOList(list);
    }
}
