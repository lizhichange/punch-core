package com.fulihui.punch.web.integration.impl;

import static org.near.servicesupport.util.ServiceResultUtil.checkResult;

import java.util.Date;

import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.UserPunchCountService;
import com.fulihui.punch.dto.UserPunchCountDTO;
import com.fulihui.punch.request.UserPunchCountDateRequest;
import com.fulihui.punch.web.integration.UserPunchCountServiceClient;

/**
 * @author lizhi
 */
@Component
public class UserPunchCountServiceClientImpl implements UserPunchCountServiceClient {
    @Reference(version = "1.0.0")
    UserPunchCountService userPunchCountService;

    @Override
    public TPageResult<UserPunchCountDTO> queryPage(UserPunchCountDateRequest request) {
        TPageResult<UserPunchCountDTO> result = userPunchCountService.queryPage(request);
        checkResult(result);
        return result;
    }

    @Override
    public TPageResult<UserPunchCountDTO> queryPageFilter(UserPunchCountDateRequest request) {
        TPageResult<UserPunchCountDTO> result = userPunchCountService.queryPageFilter(request);
        checkResult(result);
        return result;
    }

    @Override
    public UserPunchCountDTO queryUserId(String userId, Date startDate,
                                         /**
                                          * 结束时间
                                          */
                                         Date endDate) {
        UserPunchCountDateRequest request = new UserPunchCountDateRequest();
        request.setUserId(userId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        TSingleResult<UserPunchCountDTO> result = userPunchCountService.queryUserId(request);
        checkResult(result);
        return result.getValue();
    }
}
