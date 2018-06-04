package com.fulihui.punch.model;

import org.near.toolkit.model.ToString;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lz
 */
@Getter
@Setter
public class ZbusPunchModel extends ToString {
    private static final long serialVersionUID = 4562072255750274995L;
    /**
     * 打卡记录订单Id
     */
    private String            punchOrderId;
    /**
     * 用户id
     */
    private String            userId;
}
