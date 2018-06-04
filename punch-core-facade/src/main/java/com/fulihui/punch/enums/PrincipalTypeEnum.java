/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.enums;

/**
 * 主体类型枚举
 * @author Willard.Hu on 2017/10/31.
 */
public enum PrincipalTypeEnum implements IEnum<String> {
                                                        /**
                                                         * 普通用户
                                                         */
                                                        USER("1", "普通用户"),
                                                        /**
                                                         * 商户
                                                         */
                                                        MERCHANT("2", "商户"),
                                                        /**
                                                         * 平台操作员
                                                         */
                                                        ADMIN("3", "平台操作员");

    String code;
    String desc;

    PrincipalTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }
}
