package com.fulihui.punch.request;

import java.util.Date;

import org.near.servicesupport.request.AbstractServiceRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lz
 */
@Setter
@Getter
public class UserPunchUpdateRequest extends AbstractServiceRequest {
    private static final long serialVersionUID = 4699991058014648046L;
    /**
     * 订单id
     */
    private String            punchOrderId;
    /**
     * 用户id
     */
    private String            userId;
    /**
     * 外部交易号
     */
    private String            outTradeNo;

    /**
     * 状态
     */
    private String            status;
    /**
     * 支付时间
     */
    private Date              payDate;

    private String            payDecs;

}
