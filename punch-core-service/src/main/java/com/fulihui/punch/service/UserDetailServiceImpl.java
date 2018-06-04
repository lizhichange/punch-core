/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.service;

import org.near.dal.sequence.SeqKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.UserDetailService;
import com.fulihui.punch.core.repository.UserDetailRepository;
import com.fulihui.punch.core.repository.WechatAuthRepository;
import com.fulihui.punch.dal.dataobj.TUserDetail;
import com.fulihui.punch.dal.dataobj.WechatAuthDO;
import com.fulihui.punch.dto.UserDto;
import com.fulihui.punch.dto.WechatAuthDto;
import com.fulihui.punch.enums.PrincipalTypeEnum;
import com.fulihui.punch.request.UserWechatLoginRequest;

/**
 * @author Willard Hu on 2017/11/1.
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private WechatAuthRepository wechatRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDto wechatLogin(UserWechatLoginRequest request) {
        PrincipalTypeEnum principalType = PrincipalTypeEnum.USER;
        WechatAuthDto wechat = wechatRepository.queryByOpenId(request.getOpenId(), principalType);
        UserDto res;
        // 已注册
        if (wechat != null) {
            res = userDetailRepository.queryByPK(wechat.getPrincipalId());
        } else { // 未注册
            String userId = String.valueOf(SeqKit.genId());
            TUserDetail uRecord = new TUserDetail();
            uRecord.setUserId(userId);
            uRecord.setNickname(request.getNickName());
            uRecord.setGender(request.getGender());
            uRecord.setHeadImg(request.getHeadImg());
            userDetailRepository.insert(uRecord, null);

            WechatAuthDO wRecord = new WechatAuthDO();
            wRecord.setOpenId(request.getOpenId());
            wRecord.setAppid(request.getAppid());
            wRecord.setUnionid(request.getUnionid());
            wRecord.setPrincipalId(userId);
            wRecord.setPrincipalType(principalType.code());
            wechatRepository.insert(wRecord, null);

            res = userDetailRepository.queryByPK(userId);
        }
        return res;
    }

    @Override
    public UserDto queryByUserId(String userId) {
        return userDetailRepository.queryByPK(userId);
    }

    @Override
    public WechatAuthDto queryByOpenId(String openId) {
        PrincipalTypeEnum principalType = PrincipalTypeEnum.USER;
        return wechatRepository.queryByOpenId(openId, principalType);
    }

    @Override
    public WechatAuthDto queryWechatByUserId(String userId) {
        PrincipalTypeEnum principalType = PrincipalTypeEnum.USER;
        return wechatRepository.queryByPrincipalId(userId, principalType);
    }
}
