package com.fulihui.punch.web.integration;

import com.fulihui.punch.request.*;
import org.near.servicesupport.result.TPageResult;

import com.fulihui.punch.dto.PunchJackpotAmountDTO;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import org.near.servicesupport.result.TSingleResult;

import java.util.Date;

/**
 * The interface User punch service client.
 *
 * @author lz
 */
public interface UserPunchServiceClient {

    /**
     * 创建打卡订单
     *
     * @param request UserPunchCreateRequest
     * @return 创建成功返回订单id string
     */
    String createUserPunchOrder(UserPunchCreateRequest request);

    /**
     * 用户打卡
     *
     * @param userId the user id
     * @return string string
     */
    String punch(String userId);

    /**
     * 支付成功回调改状态
     *
     * @param userId       the user id
     * @param punchOrderId the punch order id
     * @return string string
     */
    String payNotifySuccess(String userId, String punchOrderId);


    /**
     * 查询支付成状态
     * 待打卡
     *
     * @param request the request
     * @return user punch record dto
     */
    UserPunchRecordDTO queryWaitPay(UserPunchUserIdRequest request);

    /**
     * Query period date user punch record dto.
     *
     * @param userId     the user id
     * @param periodDate the period date
     * @return the user punch record dto
     */
    UserPunchRecordDTO queryPeriodDate(String userId,Date periodDate);


    /**
     * Query pay ext date user punch record dto.
     *
     * @param userId     the user id
     * @param payExtDate the pay ext date
     * @return the user punch record dto
     */
    UserPunchRecordDTO queryPayExtDate(String userId,Date  payExtDate);


    UserPunchRecordDTO queryLately(String userId);

    /**
     * Query page t page result.
     *
     * @param request the request
     * @return the t page result
     */
    TPageResult<UserPunchRecordDTO> queryPage(UserPunchUserIdRequest request);

}
