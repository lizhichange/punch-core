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
public class UserPunchCreateRequest extends AbstractServiceRequest {
    private static final long serialVersionUID = 4699991058014648046L;
    /**
     * 用户ID
     */
    private String            userId;
    /**
     * 支付类型
     * @see com.fulihui.punch.enums.OrderPayTypeEnum
     */
    private String            payType;

    /**
     * 支付时间
     */
    private Date              payDate;
    /**
     * openId
     */
    private String            openId;

    /**
     * 渠道
     * 上层业务方自定义
     */
    private String            openIdChannel;
    /**
     * 支付金额
     */
    private Integer           payAmount;
    /**
     * 支付回调通知地址
     */
    private String            notifyURL;

    private String            bodyString;

    private String            hostAddress;

}
