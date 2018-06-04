package com.fulihui.punch.web.integration.impl;

import static org.near.servicesupport.util.ServiceResultUtil.checkResult;

import org.near.servicesupport.result.TSingleResult;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.UserPunchAmountService;
import com.fulihui.punch.dto.UserPunchAmountDTO;
import com.fulihui.punch.request.UserPunchAmountRequest;
import com.fulihui.punch.web.integration.UserPunchAmountServiceClient;

@Component
public class UserPunchAmountServiceClientImpl implements UserPunchAmountServiceClient {

    @Reference(version = "1.0.0")
    UserPunchAmountService userPunchAmountService;

    @Override
    public UserPunchAmountDTO queryUserAmount(String userId) {
        UserPunchAmountRequest request = new UserPunchAmountRequest();
        request.setUserId(userId);
        TSingleResult<UserPunchAmountDTO> result = userPunchAmountService.queryUserAmount(request);
        checkResult(result);
        return result.getValue();

    }
}
