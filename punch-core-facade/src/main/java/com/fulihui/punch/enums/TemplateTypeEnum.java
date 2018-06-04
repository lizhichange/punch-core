package com.fulihui.punch.enums;

import org.near.toolkit.model.BaseEnum;

/**
 *
 * Created by 1 on 2017/12/28.
 */
public enum TemplateTypeEnum implements BaseEnum {


    DAKA_SIGN("DAKA_SIGN","参与打卡成功"),
    @Deprecated
    DAKA_SUCC("DAKA_SUCC","打卡成功提醒"),
    DAKA_TIXING("DAKA_TIXING","打卡提醒"),
    USER_NOTICE("USER_NOTICE","开奖结果通知");


    String code;
    String desc;

    TemplateTypeEnum(String code, String desc) {
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
