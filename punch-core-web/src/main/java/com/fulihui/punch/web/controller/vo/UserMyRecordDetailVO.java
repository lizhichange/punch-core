package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lz
 */
@ApiModel("我的战绩详细列表视图模型")
@Setter
@Getter
public class UserMyRecordDetailVO extends ToString {

    private static final long serialVersionUID = -4909284469603967461L;
    /**
     * 参与时间
     */
    @ApiModelProperty("参与时间")
    private String            involvementTime;
    @ApiModelProperty("打卡结果描述")
    private String            resultDesc;
    @ApiModelProperty("金额")
    private String            resultAmount;
    @ApiModelProperty("是否显示")
    private Boolean           show;
}
