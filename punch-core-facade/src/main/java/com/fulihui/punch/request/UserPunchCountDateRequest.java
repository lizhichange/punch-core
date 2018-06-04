package com.fulihui.punch.request;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lz
 */
@Setter
@Getter
public class UserPunchCountDateRequest extends UserPunchUserIdRequest {
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
    /**
     * 开始时间
     */
    private Date              startDate;
    /**
     * 结束时间
     */
    private Date              endDate;

}
