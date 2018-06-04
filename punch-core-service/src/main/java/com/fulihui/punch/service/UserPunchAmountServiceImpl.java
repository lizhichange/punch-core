package com.fulihui.punch.service;

import static org.near.servicesupport.error.Errors.Commons.REQUEST_PARAMETER_ERROR;
import static org.near.servicesupport.result.ResultBuilder.succTSingle;

import org.near.servicesupport.result.TSingleResult;
import org.near.servicesupport.util.ServiceAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.UserPunchAmountService;
import com.fulihui.punch.core.repository.UserPunchAmountRepository;
import com.fulihui.punch.dto.UserPunchAmountDTO;
import com.fulihui.punch.request.UserPunchAmountRequest;

@Service("userPunchAmountService")
public class UserPunchAmountServiceImpl implements UserPunchAmountService {

    @Autowired
    private UserPunchAmountRepository userPunchAmountRepository;

    @Override
    public TSingleResult<UserPunchAmountDTO> queryUserAmount(UserPunchAmountRequest request) {
        ServiceAssert.notNull(request, REQUEST_PARAMETER_ERROR);
        ServiceAssert.notNull(request.getUserId(), REQUEST_PARAMETER_ERROR);
        UserPunchAmountDTO sumPayAmount = userPunchAmountRepository.querySumPayAmount(request.getUserId());
        return succTSingle(sumPayAmount);
    }


}
