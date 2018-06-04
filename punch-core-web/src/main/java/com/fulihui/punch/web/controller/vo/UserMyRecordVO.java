package com.fulihui.punch.web.controller.vo;

import org.near.toolkit.model.ToString;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lz
 */
@ApiModel("我的战绩VIEW视图模型")
public class UserMyRecordVO extends ToString {
    private static final long  serialVersionUID = 7037304613168621847L;
    /** 用户昵称 */

    @ApiModelProperty("用户昵称")
    private String             nickname;
    /** 头像 */
    @ApiModelProperty("头像")
    private String             headImg;
    @ApiModelProperty("我的累计视图模型")
    private UserMyCumulativeVO cumulativeVO;

    public UserMyCumulativeVO getCumulativeVO() {
        return cumulativeVO;
    }

    public void setCumulativeVO(UserMyCumulativeVO cumulativeVO) {
        this.cumulativeVO = cumulativeVO;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
