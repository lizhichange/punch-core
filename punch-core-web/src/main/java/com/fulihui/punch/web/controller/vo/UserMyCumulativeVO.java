package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The type User my cumulative vo.
 * @author lz
 */
@ApiModel("我的累计视图模型")
@Setter
@Getter
public class UserMyCumulativeVO extends ToString {
    private static final long serialVersionUID = 532439256468816924L;
    @ApiModelProperty("累计投入")
    private String            cumulativeInput;
    @ApiModelProperty("累计赚取")
    private String            cumulativeEarning;
    @ApiModelProperty("成功打卡")
    private Integer           successfulPunch;

}
