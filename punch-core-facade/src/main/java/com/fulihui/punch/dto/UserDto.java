package com.fulihui.punch.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Willard Hu on 2017/11/1.
 */
@Getter
@Setter
public class UserDto extends BasicDto {
    private static final long serialVersionUID = -4693715301797539892L;

    /** 用户唯一标识 */
    private String            userId;
    /** 昵称 */
    private String            nickName;
    /** 用户姓名 */
    private String            name;
    /** 用户性别 */
    private String            gender;
    /** 身份证 */
    private String            idCard;
    /** 手机号 */
    private String            mobileNo;
    /** 生日 yyyyMMdd */
    private String            birthday;
    /**
     * 用户头像
     */
    private String            headImg;
}
