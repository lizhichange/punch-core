package com.fulihui.punch.request;

import java.util.List;

import org.near.servicesupport.request.PageRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lz
 */
@Setter
@Getter
public class UserPunchUserIdRequest extends PageRequest {
    private static final long serialVersionUID = 4303266724814077227L;
    /**
     * 用户id
     */
    private String            userId;

    /**
     * 状态
     * @see com.fulihui.punch.enums.OrderStatusEnum
     */
    private List<String>      statusList;

}
