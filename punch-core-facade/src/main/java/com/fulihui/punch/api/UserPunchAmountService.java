package com.fulihui.punch.api;

import org.near.servicesupport.result.TSingleResult;

import com.fulihui.punch.dto.UserPunchAmountDTO;
import com.fulihui.punch.request.UserPunchAmountRequest;

/**
 * The interface User punch amount service.
 * @author lz
 */
public interface UserPunchAmountService {

    TSingleResult<UserPunchAmountDTO> queryUserAmount(UserPunchAmountRequest request);

}
