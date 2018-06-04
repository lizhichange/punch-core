package com.fulihui.punch.dto;

import java.util.Date;

import org.near.toolkit.model.ToString;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@Setter
@Getter
public class UserPunchCountDTO extends ToString {
    private static final long serialVersionUID = 1932133584455651031L;
    private Integer           id;

    private String            userId;

    private Date              lastTime;

    private Integer           continuousCount;

    private Integer           totalCount;

    private Date              gmtCreate;

    private Date              gmtModified;

    private Date              signTime;
}
