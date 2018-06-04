package com.fulihui.punch.enums;

import org.near.toolkit.model.BaseEnum;

/**
 * Created by 1 on 2017/12/28.
 */
public enum MessageTypeEnum implements BaseEnum {

                                                 /**
                                                  *   微信消息
                                                  */
                                                 WEIXIN_MESSAGE("WEIXIN_MESSAGE", "微信消息"),
                                                 /**
                                                  * 电子邮件消息
                                                  */
                                                 EMAIL_MESSAGE("EMAIL_MESSAGE", "电子邮件消息");

    private String code;
    private String desc;

    MessageTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
