package com.fulihui.punch.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@Setter
@Getter
public class WeChatPayNotifyRequest extends UserPunchUserIdRequest {
    private static final long serialVersionUID = 5623503522240052199L;
    /**
     * 打卡订单号
     */
    private String            punchOrderId;
    
    /**
     * 支付失败描述
     */
    private String            payFailDesc;
}
