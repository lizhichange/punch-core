package com.fulihui.punch.api;

import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;

import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.request.UserPunchCreateRequest;
import com.fulihui.punch.request.UserPunchPayExtDateRequest;
import com.fulihui.punch.request.UserPunchPeriodDateRequest;
import com.fulihui.punch.request.UserPunchUpdateRequest;
import com.fulihui.punch.request.UserPunchUserIdRequest;
import com.fulihui.punch.request.WeChatPayNotifyRequest;
import com.fulihui.punch.request.WeixinAccountRequest;

/**
 * The interface User punch service.
 *
 * @author lz
 */
public interface UserPunchService {
    /**
     * 创建订单
     *
     * @param request UserPunchCreateRequest
     * @return 创建成功返回订单id t single result
     */
    TSingleResult<String> createUserPunchOrder(UserPunchCreateRequest request);

    /**
     * 查询用户待打卡状态
     * 待打卡就 支付成功
     *
     * @param request UserPunchRecordDTO
     * @return UserPunchRecordDTO t single result
     */
    TSingleResult<UserPunchRecordDTO> queryWaitPay(UserPunchUserIdRequest request);

    /**
     * 查询用户最近一条打卡记录
     *
     * @param request the request
     * @return t single result
     */
    TSingleResult<UserPunchRecordDTO> queryLately(UserPunchUserIdRequest request);

    /**
     * Query period date t single result.
     *
     * @param request the request
     * @return the t single result
     */
    TSingleResult<UserPunchRecordDTO> queryPeriodDate(UserPunchPeriodDateRequest request);

    /**
     * Query pay ext date t single result.
     *
     * @param request the request
     * @return the t single result
     */
    TSingleResult<UserPunchRecordDTO> queryPayExtDate(UserPunchPayExtDateRequest request);

    /**
     * 支付成功回调通知
     *
     * @param request the request
     * @return t single result
     */
    TSingleResult<String> payNotifySuccess(WeChatPayNotifyRequest request);

    /**
     * 根据  用户id  状态状态 倒序
     *
     * @param request the request
     * @return UserPunchRecordDTO 倒序
     */
    TSingleResult<UserPunchRecordDTO> queryUserPunch(UserPunchUserIdRequest request);

    /**
     * 用户打卡
     *
     * @param request the request
     * @return t single result
     */
    TSingleResult<String> punch(UserPunchUserIdRequest request);

    /**
     * 更新数据
     *
     * @param request the request
     * @return t single result
     */
    TSingleResult<Boolean> update(UserPunchUpdateRequest request);

    /**
     * 分页查询
     *
     * @param request the request
     * @return the t page result
     */
    TPageResult<UserPunchRecordDTO> queryPage(UserPunchUserIdRequest request);

    /**
     * Pay notify fail t single result.
     *
     * @param request the request
     * @return the t single result
     */
    TSingleResult<String> payNotifyFail(WeChatPayNotifyRequest request);


    /**
     * Weixin transfer account t single result.
     *
     * @param request the request
     * @return the t single result
     */
    TSingleResult<Boolean> weixinTransferAccount(WeixinAccountRequest request);

}
