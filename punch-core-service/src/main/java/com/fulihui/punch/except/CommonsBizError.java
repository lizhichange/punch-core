package com.fulihui.punch.except;

import org.near.servicesupport.error.ServiceError;

/**
 * Created by IntelliJ IDEA.
 * Date: 2018-3-7
 * Time: 10:27
 * @author lizhi
 */
public enum CommonsBizError implements ServiceError {
                                                     /**
                                                      * 打卡时间不能和支付时间是同一天
                                                      */
                                                     SYSTEM_ERROR(102, "打卡时间不能和支付时间是同一天"),

    ;

    int    errcode;
    String errmsg;

    CommonsBizError(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    @Override
    public int getErrcode() {
        return errcode;
    }

    @Override
    public String getErrmsg() {
        return errmsg;
    }
}