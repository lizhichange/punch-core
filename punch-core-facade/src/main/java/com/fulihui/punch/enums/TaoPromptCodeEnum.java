package com.fulihui.punch.enums;

import org.near.toolkit.model.BaseEnum;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-3-13
 * Time: 16:28
 * @author Administrator
 */
public enum TaoPromptCodeEnum implements BaseEnum {
                                                   /**
                                                    * 淘客拉新活动规则
                                                    */
                                                   RULE("RULE", "淘客拉新活动规则"),
                                                   /**
                                                    * 瓶瓶有奖活动规则
                                                    */
                                                   BOTTLE_RULE("BOTTLE_RULE", "瓶瓶有奖活动规则"),
                                                   /**
                                                    * 码上有钱活动规则
                                                    */
                                                   CODE_RULE("CODE_RULE", "码上有钱活动规则"),

                                                   /**
                                                    * 淘客拉新活动公告
                                                    */
                                                   ANNOUNCEMENT("ANNOUNCEMENT", "淘客拉新活动公告");

    private String code;
    private String desc;

    TaoPromptCodeEnum(String code, String desc) {
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