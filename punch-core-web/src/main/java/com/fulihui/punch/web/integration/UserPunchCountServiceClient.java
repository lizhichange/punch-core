package com.fulihui.punch.web.integration;

import java.util.Date;

import org.near.servicesupport.result.TPageResult;

import com.fulihui.punch.dto.UserPunchCountDTO;
import com.fulihui.punch.request.UserPunchCountDateRequest;

public interface UserPunchCountServiceClient {

    TPageResult<UserPunchCountDTO> queryPage(UserPunchCountDateRequest request);

    TPageResult<UserPunchCountDTO> queryPageFilter(UserPunchCountDateRequest request);

    UserPunchCountDTO queryUserId(String userId, Date startDate, Date endDate);

}
