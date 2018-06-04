package com.fulihui.punch.core.repository.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fulihui.punch.core.repository.PunchJackpotAmountRepository;
import com.fulihui.punch.dal.dataobj.PunchJackpotAmount;
import com.fulihui.punch.dal.dataobj.PunchJackpotAmountExample;
import com.fulihui.punch.dal.mapper.ExtPunchJackpotAmountMapper;
import com.fulihui.punch.dal.mapper.PunchJackpotAmountMapper;
import com.fulihui.punch.dto.PunchJackpotAmountDTO;

@Repository
public class PunchJackpotAmountRepositoryImpl implements PunchJackpotAmountRepository {

    @Autowired
    PunchJackpotAmountMapper    punchJackpotAmountMapper;
    @Autowired
    ExtPunchJackpotAmountMapper extPunchJackpotAmountMapper;

    @Override
    public PunchJackpotAmountDTO queryPeriodDate(Date date) {
        PunchJackpotAmountExample example = new PunchJackpotAmountExample();
        example.createCriteria().andPeriodDateEqualTo(date);
        List<PunchJackpotAmount> list = punchJackpotAmountMapper.selectByExample(example);
        if (isEmpty(list)) {
            return null;
        }
        List<PunchJackpotAmountDTO> collect = toList(list);
        return collect.get(0);
    }

    @Override
    public List<PunchJackpotAmountDTO> queryMaxPeriodDate() {
        List<PunchJackpotAmount> list = extPunchJackpotAmountMapper.queryMaxPeriodDate();
        return toList(list);

    }

    @Override
    public int save(PunchJackpotAmountDTO dto) {
        PunchJackpotAmount record = new PunchJackpotAmount();
        BeanUtils.copyProperties(dto, record);
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        return punchJackpotAmountMapper.insertSelective(record);

    }

    @Override
    public int update(PunchJackpotAmountDTO dto) {
        PunchJackpotAmount record = new PunchJackpotAmount();
        BeanUtils.copyProperties(dto, record);
        record.setGmtModified(new Date());
        return punchJackpotAmountMapper.updateByPrimaryKeySelective(record);

    }

    private List<PunchJackpotAmountDTO> toList(List<PunchJackpotAmount> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private PunchJackpotAmountDTO toDTO(PunchJackpotAmount item) {
        PunchJackpotAmountDTO dto = new PunchJackpotAmountDTO();
        BeanUtils.copyProperties(item, dto);
        return dto;
    }
}
