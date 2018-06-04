package com.fulihui.punch.request;

import java.util.Date;

import org.near.servicesupport.request.PageRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@Setter
@Getter
public class PunchJackpotAmountRequest extends PageRequest {
    private static final long serialVersionUID = 4303266724814077227L;
    /**
     * 期号时间
     * webFormat
     * 时间格式
     * yyyy-MM-dd
     */
    private Date              periodDate;

}
