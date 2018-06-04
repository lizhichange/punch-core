package com.fulihui.punch.enums;

import org.near.toolkit.model.BaseEnum;

/**
 * 支付方式
 * @author lz
 */
public enum OrderPayTypeEnum implements BaseEnum {
                                                  /**
                                                   * 微信支付
                                                   */
                                                  WECHAT_PAY("WECHAT_PAY", "微信", "am-icon-weixin"),

    ;

    private String code;
    private String desc;
    private String iconUI;

    OrderPayTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    OrderPayTypeEnum(String code, String desc, String iconUI) {
        this.code = code;
        this.desc = desc;
        this.iconUI = iconUI;
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

    public String getIconUI() {
        return iconUI;
    }

    public void setIconUI(String iconUI) {
        this.iconUI = iconUI;
    }
}
