/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.core.repository;


import com.fulihui.punch.dal.dataobj.WechatTokenDO;
import com.fulihui.systemcore.enums.WechatTokenTypeEnum;

/**
 * @author Willard.Hu on 2017/10/31.
 */
public interface WechatTokenRepository {

    void insert(WechatTokenDO record, String operator);

    boolean update(WechatTokenDO record, String operator);

    WechatTokenDO queryByType(String appid, WechatTokenTypeEnum tokenType);
}
