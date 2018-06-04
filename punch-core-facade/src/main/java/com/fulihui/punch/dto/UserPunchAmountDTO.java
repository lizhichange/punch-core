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
public class UserPunchAmountDTO extends ToString {
    /**
     * 
     */
    private static final long serialVersionUID = -7533942668353486710L;

    private String            userId;

    private Integer           payAmount;

    private Integer           makeAmount;

    private Date              gmtCreate;

    private Date              gmtModified;

    private Date              periodDate;

}