package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@ApiModel(description = "查询用户当前状态返回模型")
@Setter
@Getter
public class UserPunchStatusVO extends ToString {
    private static final long serialVersionUID = 5323018426828341923L;
    @ApiModelProperty("用户Id")
    private String            userId;
    @ApiModelProperty("用户状态,status=WAIT_PAY_SUCCESS(待打卡==到计时) " + "status=WAIT_NO_PAY(提示用户支付)"
                      + "status=CAN_HIT_PAY(提示用户可以打卡)")
    private String            status;
    @ApiModelProperty("倒计时")
    private Long              surplusSecond;
    @ApiModelProperty("打卡失败字段,failStatus=WAIT_NOT_CARE(打卡失败)")
    private String            failStatus;
    @ApiModelProperty("是否显示,display=(true显示,false不显示)")
    private Boolean           display;

}
