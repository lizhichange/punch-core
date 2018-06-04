package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@ApiModel("打卡状况视图模型")
@Setter
@Getter
public class SituationVO extends ToString {

    private static final long serialVersionUID = -3791298755487171865L;
    @ApiModelProperty("成功打卡数量")
    private Integer           successNum;
    @ApiModelProperty("失败打卡数量")
    private Integer           failNum;
    @ApiModelProperty("时间")
    private String            situationTime;


}
