package com.fulihui.punch.web.integration.impl;

import static org.near.servicesupport.util.ServiceResultUtil.checkResult;

import java.util.Date;

import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulihui.punch.api.UserPunchService;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.request.*;
import com.fulihui.punch.web.integration.UserPunchServiceClient;

/**
 * @author lz
 */
@Component
public class UserPunchServiceClientImpl implements UserPunchServiceClient {
    @Autowired
    UserPunchService userPunchService;

    @Override
    public String createUserPunchOrder(UserPunchCreateRequest request) {
        TSingleResult<String> result = userPunchService.createUserPunchOrder(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public String punch(String userId) {
        UserPunchUserIdRequest request = new UserPunchUserIdRequest();
        request.setUserId(userId);
        TSingleResult<String> result = userPunchService.punch(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public String payNotifySuccess(String userId, String punchOrderId) {
        WeChatPayNotifyRequest request = new WeChatPayNotifyRequest();
        request.setUserId(userId);
        request.setPunchOrderId(punchOrderId);
        TSingleResult<String> result = userPunchService.payNotifySuccess(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public UserPunchRecordDTO queryWaitPay(UserPunchUserIdRequest request) {
        TSingleResult<UserPunchRecordDTO> result = userPunchService.queryWaitPay(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public TPageResult<UserPunchRecordDTO> queryPage(UserPunchUserIdRequest request) {
        TPageResult<UserPunchRecordDTO> result = userPunchService.queryPage(request);
        checkResult(result);
        return result;
    }

    @Override
    public UserPunchRecordDTO queryPeriodDate(String userId, Date periodDate) {
        UserPunchPeriodDateRequest request = new UserPunchPeriodDateRequest();
        request.setPeriodDate(periodDate);
        request.setUserId(userId);
        TSingleResult<UserPunchRecordDTO> result = userPunchService.queryPeriodDate(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public UserPunchRecordDTO queryPayExtDate(String userId, Date payExtDate) {
        UserPunchPayExtDateRequest request = new UserPunchPayExtDateRequest();
        request.setPayExtDate(payExtDate);
        request.setUserId(userId);
        TSingleResult<UserPunchRecordDTO> result = userPunchService.queryPayExtDate(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public UserPunchRecordDTO queryLately(String userId) {
        UserPunchUserIdRequest request = new UserPunchUserIdRequest();
        request.setUserId(userId);
        TSingleResult<UserPunchRecordDTO> result = userPunchService.queryLately(request);
        checkResult(result);
        return result.getValue();
    }

}
