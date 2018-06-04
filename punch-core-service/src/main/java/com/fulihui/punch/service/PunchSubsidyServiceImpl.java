package com.fulihui.punch.service;

import static org.near.servicesupport.error.Errors.Commons.REQUEST_PARAMETER_ERROR;
import static org.near.servicesupport.util.ServiceAssert.notNull;

import java.util.Date;
import java.util.List;

import org.near.servicesupport.result.ResultBuilder;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.PunchSubsidyService;
import com.fulihui.punch.biz.conv.PunchSubsidyInfoConv;
import com.fulihui.punch.core.repository.PunchSubsidyInfoRepository;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfo;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfoExample;
import com.fulihui.punch.dto.PunchSubsidyInfoDTO;
import com.fulihui.punch.enums.SubsidyStatusEnum;
import com.fulihui.punch.request.PunchSubsidyInfoRequest;
import com.fulihui.punch.request.PunchSubsidyInfoUpdateRequest;

@Service("punchSubsidyService")
public class PunchSubsidyServiceImpl implements PunchSubsidyService {

    private static final Logger        LOGGER = LoggerFactory
        .getLogger(PunchSubsidyServiceImpl.class);

    @Autowired
    private PunchSubsidyInfoRepository punchSubsidyInfoRepository;
    

    @Override
    public TPageResult<PunchSubsidyInfoDTO> queryPage(PunchSubsidyInfoRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        PunchSubsidyInfoExample example = genExample(request);
        List<PunchSubsidyInfoDTO> queryPage = punchSubsidyInfoRepository.queryPage(example);
        Integer count = punchSubsidyInfoRepository.count(example);
        return ResultBuilder.succTPage(queryPage, request.getPage(), request.getRows(), count);
    }

    @Override
    public TSingleResult<Boolean> updateStatus(PunchSubsidyInfoUpdateRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notNull(request.getId(), REQUEST_PARAMETER_ERROR);
        PunchSubsidyInfoDTO dto = new PunchSubsidyInfoDTO();
        PunchSubsidyInfo punchSubsidyInfo = punchSubsidyInfoRepository
            .selectByPrimaryKey(request.getId());

        if (punchSubsidyInfo != null) {
            String punchStatus = punchSubsidyInfo.getStatus();
            //没有状态
            if (punchStatus.equals(SubsidyStatusEnum.END.getCode())) {
                ResultBuilder.failTSingle(103, "结束的数据不能修改");
            }
            Long subsidyAmount = request.getSubsidyAmount();
            if (subsidyAmount != null) {
                dto.setSubsidyAmount(subsidyAmount);

            }
            String status = request.getStatus();
            if (status != null) {
                dto.setStatus(status);
            }
            dto.setId(request.getId());
            boolean updateStatus = punchSubsidyInfoRepository.updateStatus(dto);
            if (updateStatus) {
                return ResultBuilder.succTSingle(true);
            } else {
                return ResultBuilder.failTSingle(102, "更新失败");
            }
        }
        return ResultBuilder.succTSingle(true);

    }

    private PunchSubsidyInfoExample genExample(PunchSubsidyInfoRequest request) {
        PunchSubsidyInfoExample example = new PunchSubsidyInfoExample();
        PunchSubsidyInfoExample.Criteria criteria = example.createCriteria();

        example.setOffset(request.start4Mysql());
        example.setLimit(request.getRows());
        example.setOrderByClause("as_date desc");
        if (request.getStatus() != null) {
            criteria.andStatusEqualTo(request.getStatus());
        }
        if (request.getAsDate() != null) {
            criteria.andAsDateEqualTo(request.getAsDate());
        }
        if (request.getStartDate() != null) {
            criteria.andAsDateGreaterThanOrEqualTo(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            criteria.andAsDateLessThanOrEqualTo(request.getEndDate());
        }
        return example;
    }

    @Override
    public TSingleResult<PunchSubsidyInfoDTO> queryById(Integer id) {
        notNull(id, REQUEST_PARAMETER_ERROR);
        PunchSubsidyInfo punchSubsidyInfo = punchSubsidyInfoRepository.selectByPrimaryKey(id);
        PunchSubsidyInfoDTO dto = PunchSubsidyInfoConv.toDTO(punchSubsidyInfo);
        return ResultBuilder.succTSingle(dto);
    }

    @Override
    public TSingleResult<String> queryRedisByDate(Date date) {
        notNull(date, REQUEST_PARAMETER_ERROR);
        String queryAmount = punchSubsidyInfoRepository.queryAmount(date);
        return ResultBuilder.succTSingle(queryAmount);
    }

}
