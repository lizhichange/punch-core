package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lz
 */
@ApiModel(description = "累计参与人数奖池金额VIEW模型")
@Setter
@Getter
public class CumulativeVO extends ToString {
    private static final long serialVersionUID = -362584524447401399L;
    /**
     * 金额
     */
    @ApiModelProperty("奖池金额")
    private Integer           cumulativeAmount;
    /**
     * 累计人数
     */
    @ApiModelProperty("累计人数")
    private Integer           cumulativeNumber;

    @ApiModelProperty("奖池状态,针对于人,status=today(今日),status=tomorrow(明日)")
    private String            status;

}
