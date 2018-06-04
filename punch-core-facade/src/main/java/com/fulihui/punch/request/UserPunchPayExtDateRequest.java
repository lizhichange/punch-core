package com.fulihui.punch.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LZ
 */
@Setter
@Getter
public class UserPunchPayExtDateRequest extends UserPunchUserIdRequest {
    private static final long serialVersionUID = 85083570610604212L;
    /**
     * 期号时间
     * 时间格式 yyyy-MM-dd
     */
    private Date              payExtDate;
}
