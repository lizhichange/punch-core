/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.core.repository;


import com.fulihui.punch.dal.dataobj.WechatAuthDO;
import com.fulihui.punch.dto.WechatAuthDto;
import com.fulihui.punch.enums.PrincipalTypeEnum;

/**
 * @author Willard.Hu on 2017/10/31.
 */
public interface WechatAuthRepository {

    void insert(WechatAuthDO record, String operator);

    boolean update(WechatAuthDO record, String operator);

    boolean delete(String principalId, PrincipalTypeEnum principalType);

    WechatAuthDto queryByOpenId(String openId, PrincipalTypeEnum principalType);

    WechatAuthDto queryByPrincipalId(String principalId, PrincipalTypeEnum principalType);
}
