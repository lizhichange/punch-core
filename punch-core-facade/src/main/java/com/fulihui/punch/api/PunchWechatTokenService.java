package com.fulihui.punch.api;


import com.fulihui.punch.request.WechatTokenQueryRequest;

/**
 * @author Willard Hu on 2017/10/31.
 */
public interface PunchWechatTokenService {

    /**
     * 获取微信普通接口票据
     * @param request {@link WechatTokenQueryRequest}
     * @return 票据
     */
    String takeAccessToken(WechatTokenQueryRequest request);

    /**
     * 获取微信 JS-SDK 票据
     * @param request {@link WechatTokenQueryRequest}
     * @return 票据
     */
    String takeJsapiTicket(WechatTokenQueryRequest request);
}
