package com.fulihui.punch.web.integration.impl;

import org.near.servicesupport.result.TSingleResult;
import org.near.servicesupport.util.ServiceResultUtil;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.web.integration.UserServiceClient;
import com.fulihui.usercore.api.UserService;
import com.fulihui.usercore.dto.UserDTO;
import com.fulihui.usercore.request.user.UserSingleRequest;

/**
 * @author lz
 */
@Component
public class UserServiceClientImpl implements UserServiceClient {
    @Reference(version = "1.0.1")
    UserService userService;

    @Override
    public UserDTO querySingle(String userId) {
        UserSingleRequest request = new UserSingleRequest();
        request.setUserId(userId);
        TSingleResult<UserDTO> result = userService.querySingle(request);
        ServiceResultUtil.checkResult(result);
        return result.getValue();
    }
}
