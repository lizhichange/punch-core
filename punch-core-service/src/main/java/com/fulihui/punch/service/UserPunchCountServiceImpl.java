package com.fulihui.punch.service;

import static org.near.servicesupport.result.ResultBuilder.succTPage;
import static org.near.servicesupport.result.ResultBuilder.succTSingle;
import static org.near.servicesupport.util.ServiceAssert.notNull;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import org.near.servicesupport.error.Errors;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;
import org.near.toolkit.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.UserPunchCountService;
import com.fulihui.punch.core.repository.UserPunchCountRepository;
import com.fulihui.punch.dal.dataobj.UserPunchCountExample;
import com.fulihui.punch.dto.UserPunchCountDTO;
import com.fulihui.punch.request.UserPunchCountDateRequest;

@Service("userPunchCountService")
public class UserPunchCountServiceImpl implements UserPunchCountService {
    @Autowired
    UserPunchCountRepository userPunchCountRepository;

    @Override
    public TPageResult<UserPunchCountDTO> queryPage(UserPunchCountDateRequest request) {

        notNull(request, Errors.Commons.REQUEST_PARAMETER_ERROR);
        UserPunchCountExample example = toExample(request);
        int offset = request.getPage() > 1 ? (request.getPage() - 1) * request.getRows() : 0;
        example.setOffset(offset);
        example.setLimit(request.getRows());

        List<UserPunchCountDTO> query = userPunchCountRepository.query(example);

        long count = userPunchCountRepository.count(toExample(request));

        return succTPage(query, request.getPage(), request.getRows(), (int) count);
    }

    @Override
    public TPageResult<UserPunchCountDTO> queryPageFilter(UserPunchCountDateRequest request) {
        notNull(request, Errors.Commons.REQUEST_PARAMETER_ERROR);
        UserPunchCountExample example = getUserIdNotEqualTo(request);
        int offset = request.getPage() > 1 ? (request.getPage() - 1) * request.getRows() : 0;
        example.setOffset(offset);
        example.setLimit(request.getRows());
        List<UserPunchCountDTO> query = userPunchCountRepository.query(example);
        long count = userPunchCountRepository.count(getUserIdNotEqualTo(request));
        return succTPage(query, request.getPage(), request.getRows(), (int) count);
    }

    private UserPunchCountExample getUserIdNotEqualTo(UserPunchCountDateRequest request) {
        UserPunchCountExample example = new UserPunchCountExample();
        UserPunchCountExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(request.getUserId())) {
            criteria.andUserIdNotEqualTo(request.getUserId());
        }
        criteria.andContinuousCountGreaterThan(0);
        if (request.getStartDate() != null) {
            //大于等于
            criteria.andSignTimeGreaterThanOrEqualTo(request.getStartDate());
        }
        //小于等于
        if (request.getEndDate() != null) {
            criteria.andSignTimeLessThanOrEqualTo(request.getEndDate());
        }
        example.setOrderByClause(" sign_time desc");
        return example;
    }

    @Override
    public TSingleResult<UserPunchCountDTO> queryUserId(UserPunchCountDateRequest request) {
        notNull(request, Errors.Commons.REQUEST_PARAMETER_ERROR);
        notNull(request.getUserId(), Errors.Commons.REQUEST_PARAMETER_ERROR);
        UserPunchCountExample example = toExample(request);
        List<UserPunchCountDTO> query = userPunchCountRepository.query(example);
        if (!isEmpty(query)) {
            return succTSingle(query.get(0));
        }
        return succTSingle(null);
    }

    private UserPunchCountExample toExample(UserPunchCountDateRequest request) {
        UserPunchCountExample example = new UserPunchCountExample();
        UserPunchCountExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(request.getUserId())) {
            criteria.andUserIdEqualTo(request.getUserId());
        }
        if (request.getStartDate() != null) {
            //大于等于
            criteria.andSignTimeGreaterThanOrEqualTo(request.getStartDate());
        }
        //小于等于
        if (request.getEndDate() != null) {
            criteria.andSignTimeLessThanOrEqualTo(request.getEndDate());
        }
        return example;
    }
}
