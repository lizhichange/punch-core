package com.fulihui.punch.enums;

import org.near.toolkit.model.BaseEnum;

public enum RuleTypeEnum implements BaseEnum {
                                              /**
                                               * 时
                                               */
                                              T("T", "时"),

                                              /**
                                               * 分
                                               */
                                              M("M", "分"),;

    String code;
    String desc;

    RuleTypeEnum(String code, String desc) {
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
