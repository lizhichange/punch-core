package com.fulihui.punch.enums;

import org.near.toolkit.model.BaseEnum;

/**
 * @author lz
 */

public enum SubsidyStatusEnum implements BaseEnum {
                                                   /**
                                                    * 初始化
                                                    */
                                                   INIT("INIT", "配置中"),
                                                   /**
                                                    * 上线中
                                                    */
                                                   ONLINE("ONLINE", "已上线"),

                                                   /**
                                                    * END
                                                    */
                                                   END("END", "结束");

    private String code;
    private String desc;

    SubsidyStatusEnum(String code, String desc) {
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
