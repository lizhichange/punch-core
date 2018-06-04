package com.fulihui.punch.util;

import org.near.servicesupport.error.ServiceError;

/**
 * Created by lz on 2017-1-9.
 */
public enum BizErrorEnum implements ServiceError {

                                                  USER_EXISTS(102, "活动用户信息已经存在"),

                                                  AWARD_EXISTS(103, "奖品信息已经存在"),

                                                  TOTAL_ERROR(104, "总数量不能小于剩余数量"),

                                                  TOTAL_AMOUNT_ERROR(105, "金额生成活动规则总金额计算有误"),

                                                  THRESHOLD_ERROR(106, "规则百分比计算有误"),

                                                  END_APPEND_ERROR(107, "活动时间已结束,不能追加金额"),

                                                  OFF_OF_ON_ERROR(108, "活动已下线过,不能再上线"),

                                                  INIT_TO_ON_ERROR(109, "已有进行中的活动,该任务不能上线"),

                                                  EFFECT_ON_ERROR(110, "该活动已经失效,不能上线"),

                                                  CREATE_TIME_ERROR(111, "该区域时间段已存在"),

                                                  CONFIG_TYPE_ERROR(112, "配置类型已经存在"),

                                                  CONFIG_IS_NULL(113, "配置类型不存在"),

                                                  CREATE_REGION_ERROR(114, "该区域关联配置已存在"),

                                                  REQUEST_PARAMETER_ERROR(101, "请求参数错误"),

                                                  INIT_TO_EDIT_ERROR(115, "进行中配置,不能修改"),

                                                  CUSTOM_DATE_ERROR(116, "每天时间段区间必须保持相隔1天内"),

                                                  CUSTOM_INT_ERROR(117, "该时间段该地区已有进行中配置"),

                                                  ACTIVITY_ERROR(118, "活动未配置或已经发送完毕"),

                                                  BANNER_CONFIG_ERROR(119, "Banner配置进行中不能超过3个"),

                                                  CHANGE_CONFIG_ERROR(120, "活动已结束,不能上线"),

                                                  CHANGE_ING_ERROR(121, "该活动已下线过,不能上线"),

                                                  NOW_END_ERROR(122, "活动结束时间不能小于当前时间"),

                                                  NOW_START_ERROR(123, "活动开始时间不能小于当前时间"),

                                                  TIME_END_ERROR(124, "活动开始时间不能大于等于活动结束时间"),

    ;

    private int    errcode;
    private String errmsg;

    BizErrorEnum(int errcode, String errmsg) {
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
