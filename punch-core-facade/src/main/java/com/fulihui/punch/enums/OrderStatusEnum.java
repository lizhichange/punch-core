package com.fulihui.punch.enums;

import org.near.toolkit.model.BaseEnum;

/**
 * 用户打卡状态
 *
 * @author lz
 * @date
 */
public enum OrderStatusEnum implements BaseEnum {

                                                 /**
                                                  *初始状态
                                                  */
                                                 WAIT_NO_PAY("WAIT_NO_PAY", "待支付"),
                                                 /**
                                                  *支付失败
                                                  */
                                                 WAIT_PAY_FAIL("WAIT_PAY_FAIL", "支付失败"),
                                                 /**
                                                  *支付中
                                                  */
                                                 WAIT_ING_PAY("WAIT_ING_PAY", "支付中"),
                                                 /**
                                                  *支付成功
                                                  */
                                                 WAIT_PAY_SUCCESS("WAIT_PAY_SUCCESS", "支付成功"),
                                                 /**
                                                  *已打卡
                                                  */
                                                 WAIT_ALREADY("WAIT_ALREADY", "已打卡"),
                                                 /**
                                                  *未打卡
                                                  */
                                                 WAIT_NOT_CARE("WAIT_NOT_CARE", "未打卡"),

                                                 /**
                                                  *已完成
                                                  */
                                                 COMPLETED("COMPLETED", "已完成"),;

    private String code;
    private String desc;

    OrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
