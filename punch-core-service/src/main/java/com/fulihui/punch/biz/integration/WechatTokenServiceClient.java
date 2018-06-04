/**
 * fulihui.com Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */
package com.fulihui.punch.biz.integration;

/**
 * wechatToken服务客户端
 * @author yunfeng.li
 * @version $Id: WechatTokenServiceClient.java, v 0.1 2016年9月9日 下午5:48:14 yunfeng.li Exp $
 */
public interface WechatTokenServiceClient {

	String getAccessToken(String appId, String appsecret);
}
