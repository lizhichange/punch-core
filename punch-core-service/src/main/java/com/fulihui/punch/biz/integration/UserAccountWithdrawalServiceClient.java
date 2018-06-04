package com.fulihui.punch.biz.integration;

import com.fulihui.accountcore.request.useraccount.UpdateWithdrawalOrderRequest;
import com.fulihui.accountcore.request.useraccount.UserApplyWithdrawalRequest;

public interface UserAccountWithdrawalServiceClient {
    
    /**
     * 提现创建订单
     * @param request
     * @return
     */
    String applyWithdrawal(UserApplyWithdrawalRequest request);
    
    /**
     * 提现更新单号
     * @param request
     * @return
     */
    boolean updateByTradeNo(UpdateWithdrawalOrderRequest request);

}
