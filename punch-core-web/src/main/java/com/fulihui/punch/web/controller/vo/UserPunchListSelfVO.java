package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;
import org.near.webmvcsupport.view.PageView;

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
public class UserPunchListSelfVO extends ToString {

    private static final long         serialVersionUID = 3094978005467360752L;
    @ApiModelProperty("信息")
    UserPunchListVO                   vo;
    @ApiModelProperty("分页信息")
    private PageView<UserPunchListVO> list;

}
