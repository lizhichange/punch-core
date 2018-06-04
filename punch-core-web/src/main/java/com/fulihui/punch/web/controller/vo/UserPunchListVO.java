package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@Setter
@Getter
@ApiModel("用户打卡战绩列表VIEW视图模型")
public class UserPunchListVO extends ToString {
    private static final long serialVersionUID = -3399678240060002952L;
    @ApiModelProperty("用户昵称")
    private String            nickName;
    @ApiModelProperty("头像")
    private String            headImg;
    @ApiModelProperty("用户连续打卡次数")
    private Integer           continuousCount;
    @ApiModelProperty("用户最近打卡时间")
    private String            punchDateStr;
    @ApiModelProperty("累计赚取金额")
    private String            myAmount;

}
