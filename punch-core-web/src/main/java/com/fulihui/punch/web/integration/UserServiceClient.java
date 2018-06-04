package com.fulihui.punch.web.integration;

import com.fulihui.usercore.dto.UserDTO;

/**
 * 用户信息操作接口
 * Created by Willard.Hu on 2016/5/16.
 */
public interface UserServiceClient {

    /**
     * 查询单个用户
     *
     * @param userId the user id
     * @return 返回单个用户信息 {@link UserDTO}
     */
    UserDTO querySingle(String userId);

}