package com.fulihui.punch.web.enums;

import org.near.toolkit.model.BaseEnum;

public enum CumulativeStatus implements BaseEnum {
                                                  /**
                                                   *     今日
                                                   */

                                                  TODAY("today", "今日"),
                                                  /**
                                                   * 明日
                                                   */
                                                  TOMORROW("tomorrow", "明日"),;

    private String code;
    private String desc;

    CumulativeStatus(String code, String desc) {
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
