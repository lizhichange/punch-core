package com.fulihui.punch.result;

import org.near.toolkit.model.ToString;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LZ
 */
@Getter
@Setter
public class TaoKeStatisticsResult extends ToString {
    private static final long serialVersionUID = 6283636327750391361L;

    private Long              succNum;

    private Long              doneNum;

    private String            userId;

    private Long              amount;

    private Long              regNum;
    private Long              bindNum;
    private Long              BuyNum;
}