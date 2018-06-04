package com.fulihui.punch.biz.integration.impl;

import org.near.servicesupport.result.TSingleResult;
import org.near.servicesupport.util.ServiceResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulihui.accountcore.api.UserAccountWithdrawalService;
import com.fulihui.accountcore.request.useraccount.UpdateWithdrawalOrderRequest;
import com.fulihui.accountcore.request.useraccount.UserApplyWithdrawalRequest;
import com.fulihui.punch.biz.integration.UserAccountWithdrawalServiceClient;

@Component
public class UserAccountWithdrawalServiceClientImpl implements UserAccountWithdrawalServiceClient{
    
    @Autowired
    private UserAccountWithdrawalService userAccountWithdrawalService;

    @Override
    public String applyWithdrawal(UserApplyWithdrawalRequest request) {
        TSingleResult<String> result = userAccountWithdrawalService.applyWithdrawal(request);
        ServiceResultUtil.checkResult(result);
        return result.getValue();
    }

    @Override
    public boolean updateByTradeNo(UpdateWithdrawalOrderRequest request) {
        TSingleResult<Boolean> result = userAccountWithdrawalService.updateByTradeNo(request);
        ServiceResultUtil.checkResult(result);
        return result.getValue();
    }

}
