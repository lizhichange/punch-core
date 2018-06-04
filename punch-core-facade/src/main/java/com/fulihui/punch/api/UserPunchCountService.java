package com.fulihui.punch.api;

import com.fulihui.punch.request.UserPunchCountDateRequest;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;

import com.fulihui.punch.dto.UserPunchCountDTO;
import com.fulihui.punch.request.UserPunchUserIdRequest;

/**
 * @author lz
 */
public interface UserPunchCountService {

    TPageResult<UserPunchCountDTO> queryPage(UserPunchCountDateRequest request);

    TPageResult<UserPunchCountDTO> queryPageFilter(UserPunchCountDateRequest request);

    TSingleResult<UserPunchCountDTO> queryUserId(UserPunchCountDateRequest request);
}
