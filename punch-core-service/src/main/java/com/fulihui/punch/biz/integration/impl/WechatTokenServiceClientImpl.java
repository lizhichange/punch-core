/**
 * fulihui.com Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */
package com.fulihui.punch.biz.integration.impl;

import com.fulihui.punch.biz.integration.WechatTokenServiceClient;
import com.fulihui.systemcore.api.WechatTokenService;
import com.fulihui.systemcore.enums.WechatTokenTypeEnum;
import com.fulihui.systemcore.request.wechat.WechatTokenRequest;
import com.fulihui.systemcore.response.wechat.WechatTokenResult;
import org.near.servicesupport.request.RequestBuilder;
import org.near.servicesupport.util.ServiceResultUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 微信获取token服务接口实现
 * @author yunfeng.li
 * @version $Id: WechatTokenServiceClientImpl.java, v 0.1 2016年9月9日 下午5:49:31 yunfeng.li Exp $
 */
@Component
public class WechatTokenServiceClientImpl implements WechatTokenServiceClient {

	/**
	 * 获取token服务
	 */
	@Resource
	private WechatTokenService wechatTokenService;

	@Override
	public String getAccessToken(String appId,String appsecret) {
		WechatTokenRequest request = RequestBuilder.build(WechatTokenRequest.class);
        request.setAppid(appId);
        request.setSecret(appsecret);
        request.setTokenType(WechatTokenTypeEnum.ACCESS_TOKEN.getCode());
        WechatTokenResult result = wechatTokenService.getToken(request);
        ServiceResultUtil.checkResult(result);
        return result.getToken();
	}
	
}
