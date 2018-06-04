package com.fulihui.punch.util;


import com.fulihui.punch.except.IError;

/**
 * 公共异常错误码区间起始值 100XXXX
 * @author Willard.Hu on 2017/12/7.
 */
public enum CommonErrors implements IError<Integer> {
    SYSTEM_ERROR(1, "系统异常，请稍后再试"),
    ILLIGEAL_REQUEST_ERROR(2, "非法请求参数"),
    NEED_LOGIN(3, "需要登陆"),
    ACCOUNT_PASSWORD_FAILED(4, "用户名密码错误"),;

    int    errcode;
    String errmsg;

    CommonErrors(int errcode, String errmsg) {
        this.errcode = 1000000 + errcode;
        this.errmsg = errmsg;
    }

    @Override
    public Integer errcode() {
        return errcode;
    }

    @Override
    public String errmsg() {
        return errmsg;
    }
}
