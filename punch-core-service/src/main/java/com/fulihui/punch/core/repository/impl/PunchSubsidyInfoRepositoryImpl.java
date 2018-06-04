package com.fulihui.punch.core.repository.impl;

import static com.alibaba.dubbo.common.utils.CollectionUtils.isEmpty;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fulihui.punch.biz.conv.PunchSubsidyInfoConv;
import com.fulihui.punch.core.repository.PunchSubsidyInfoRepository;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfo;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfoExample;
import com.fulihui.punch.dal.mapper.PunchSubsidyInfoMapper;
import com.fulihui.punch.dto.PunchSubsidyInfoDTO;
import com.fulihui.punch.enums.SubsidyStatusEnum;

@Repository
public class PunchSubsidyInfoRepositoryImpl implements PunchSubsidyInfoRepository {

    @Autowired
    private PunchSubsidyInfoMapper punchSubsidyInfoMapper;

    @Override
    public Integer create(PunchSubsidyInfoDTO dto) {
        PunchSubsidyInfo info = PunchSubsidyInfoConv.toDO(dto);
        info.setGmtCreate(new Date());
        info.setGmtModified(new Date());
        punchSubsidyInfoMapper.insertSelective(info);
        return info.getId();
    }

    @Override
    public List<PunchSubsidyInfoDTO> queryPage(PunchSubsidyInfoExample example) {
        List<PunchSubsidyInfo> list = punchSubsidyInfoMapper.selectByExample(example);
        return PunchSubsidyInfoConv.toList(list);
    }

    @Override
    public boolean updateStatus(PunchSubsidyInfoDTO dto) {
        PunchSubsidyInfo record = PunchSubsidyInfoConv.toDO(dto);
        int updateByPrimaryKeySelective = punchSubsidyInfoMapper
            .updateByPrimaryKeySelective(record);
        return updateByPrimaryKeySelective > 0;

    }

    @Override
    public Integer count(PunchSubsidyInfoExample example) {
        return punchSubsidyInfoMapper.countByExample(example);
    }

    @Override
    public PunchSubsidyInfo selectByPrimaryKey(Integer id) {
        return punchSubsidyInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public PunchSubsidyInfo queryAsDate(Date asDate) {

        PunchSubsidyInfoExample example = new PunchSubsidyInfoExample();
        example.createCriteria().andAsDateEqualTo(asDate);
        List<PunchSubsidyInfo> list = punchSubsidyInfoMapper.selectByExample(example);
        if (isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public String queryAmount(Date asDate) {
        PunchSubsidyInfo info = queryAsDate(asDate);
        if (null != info) {
            if (info.getStatus().equals(SubsidyStatusEnum.INIT.getCode())) {
                return info.getSubsidyAmount().toString();
            }
        }
        return null;
    }

}
