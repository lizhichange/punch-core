package com.fulihui.punch.request;

import java.util.Date;
import java.util.Map;

import org.near.servicesupport.request.AbstractServiceRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeixinAccountRequest extends AbstractServiceRequest {

    private static final long   serialVersionUID = -4812755597515409523L;
    private String              userId;

    private String              openId;

    private Date                periodDate;

    private String              type;

    private String              outOrderId;

    private Long                amount;

    private String              desc;

    private Map<String, Object> extInfo;

}
