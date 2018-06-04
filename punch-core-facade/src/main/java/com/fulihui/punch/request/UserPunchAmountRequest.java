package com.fulihui.punch.request;

import org.near.servicesupport.request.PageRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@Setter
@Getter
public class UserPunchAmountRequest extends PageRequest {
    private static final long serialVersionUID = 5352669024862094981L;
    /**
     * 用户id
     */

    private String userId;


}
