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
public class PunchJackpotAmountDTO extends ToString {
    private static final long serialVersionUID = -1833938829499928639L;

    private Integer           id;
    /**
     * 累计金额 单位分
     */
    private Integer           cumulativeAmount;
    /**
     * 累计人数
     */
    private Integer           cumulativeNumber;
    /**
     * 创建时间
     */
    private Date              gmtCreate;
    /**
     * 修改时间
     */
    private Date              gmtModified;
    /**
     * 期号时间
     */
    private Date              periodDate;
    /**
     * 补贴金额 单位分
     */
    private Integer           subsidyAmount;

}