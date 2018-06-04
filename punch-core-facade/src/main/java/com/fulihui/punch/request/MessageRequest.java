package com.fulihui.punch.request;

import org.near.toolkit.model.ToString;

/**
 * Created by 1 on 2017/12/28.
 */
public class MessageRequest extends ToString{
    private static final long serialVersionUID = -2775307978996915080L;
    /**
     * 模版类型
     * @see com.fulihui.punch.enums.TemplateTypeEnum
     */
    private String channel;
    private String openId;
    /**
     * 微信模板value
     */
    private String first="";
    private String keyword1="";
    private String keyword2="";
    private String keyword3="";
    private String keyword4="";
    private String keyword5="";
    private String keyword6="";
    private String keyword7="";
    private String keyword8="";
    private String remark="";


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public String getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(String keyword6) {
        this.keyword6 = keyword6;
    }

    public String getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(String keyword7) {
        this.keyword7 = keyword7;
    }

    public String getKeyword8() {
        return keyword8;
    }

    public void setKeyword8(String keyword8) {
        this.keyword8 = keyword8;
    }
}
