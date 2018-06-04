package com.fulihui.punch.web.integration;

import com.fulihui.punch.dto.WechatAuthDto;
import com.fulihui.usercore.dto.WechatUserDTO;

/**
 * The interface User wechat service client.
 *
 * @author lz
 */
public interface UserWechatServiceClient {

    /**
     * <p>通过用户标识获取指定用户微信信息</p>
     * <p>多种组合方式查询：</p>

      * <li>unionid + appid</li>
     *
     * @param unionid the unionid
     * @param appid   the appid
     * @return 返回单个微信用户信息 {@link WechatUserDTO}
     */
    WechatUserDTO querySingle(String unionid, String appid);
    /**
     * <p>通过用户标识获取指定用户微信信息</p>
     * <p>多种组合方式查询：</p>

     * <li>unionid + appid</li>
     *
     * @param openId the unionid
     * @param appid   the appid
     * @return 返回单个微信用户信息 {@link WechatUserDTO}
     */

    WechatUserDTO querySingleOpenId(String openId, String appid);
    
    
    WechatAuthDto queryByOpenId(String openId);
}
