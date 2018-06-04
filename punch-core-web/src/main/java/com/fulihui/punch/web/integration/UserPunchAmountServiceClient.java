package com.fulihui.punch.web.integration;

import com.fulihui.punch.dto.UserPunchAmountDTO;

/**
 * @author lizhi
 */
public interface UserPunchAmountServiceClient {
    UserPunchAmountDTO queryUserAmount(String userId);
}
