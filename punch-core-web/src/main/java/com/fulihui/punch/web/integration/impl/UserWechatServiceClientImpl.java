package com.fulihui.punch.web.integration.impl;

import static org.near.servicesupport.util.ServiceResultUtil.checkResult;

import org.near.servicesupport.result.TSingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.UserDetailService;
import com.fulihui.punch.dto.WechatAuthDto;
import com.fulihui.punch.web.integration.UserWechatServiceClient;
import com.fulihui.usercore.api.UserWechatService;
import com.fulihui.usercore.dto.WechatUserDTO;
import com.fulihui.usercore.request.wechat.UserWechatQueryRequest;

/**
 * @author lz
 */
@Component
public class UserWechatServiceClientImpl implements UserWechatServiceClient {

    @Reference(version = "1.0.0")
    UserWechatService userWechatService;
    
    @Autowired
    UserDetailService userDetailService;

    @Override
    public WechatUserDTO querySingle(String unionid, String appid) {
        UserWechatQueryRequest request = new UserWechatQueryRequest();
        request.setAppid(appid);
        request.setUnionid(unionid);
        TSingleResult<WechatUserDTO> result = userWechatService.querySingle(request);
        checkResult(result);
        return result.getValue();
    }

    @Override
    public WechatUserDTO querySingleOpenId(String openId, String appid) {
        UserWechatQueryRequest request = new UserWechatQueryRequest();
        request.setAppid(appid);
        request.setOpenId(openId);
        TSingleResult<WechatUserDTO> result = userWechatService.querySingle(request);
        checkResult(result);
        return result.getValue();
    }
    
    @Override
    public WechatAuthDto queryByOpenId(String openId){
        return userDetailService.queryByOpenId(openId);
    }
    

}
