package com.fulihui.punch.request;

import java.util.Date;

import org.near.servicesupport.request.PageRequest;

import lombok.Getter;
import lombok.Setter;


/**
 * @author lz
 */
@Setter
@Getter
public class SituationPeriodDateRequest extends PageRequest {
    private static final long serialVersionUID = -7168299365734036972L;

    /**
     * 期号时间
     * 时间格式 yyyy-MM-dd
     */
    private Date periodDate;


    /**
     * 期号开始时间
     * 时间格式 yyyy-MM-dd
     */
    private Date startDate;


    /**
     * 期号结束时间
     * 时间格式 yyyy-MM-dd
     */
    private Date stopDate;
}
