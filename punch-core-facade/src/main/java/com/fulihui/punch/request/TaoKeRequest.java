package com.fulihui.punch.request;

import java.util.Date;

import org.near.servicesupport.request.PageRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-3-12
 * Time: 19:11
 * @author Administrator
 */
@Setter
@Getter
public class TaoKeRequest extends PageRequest {

    private static final long serialVersionUID = 4980671396877783477L;
    private String            userId;
    /**
     * 新激活时间
    
     */
    private Date              bindTime;

    /**
     * 新注册时间
     */
    private Date              registerTime;
    /**
     * 状态
     */
    private String            status;
    /**
     * unionId
     */
    private String            unionId;
    /**
     * 首购时间
    
     */
    private Date              buyTime;

    private Long              tbTradeParentId;

    private Date              beginTime;

    private Date              endTime;

}
