package com.fulihui.punch.result;

import org.near.toolkit.model.ToString;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhi
 */
@Setter
@Getter
public class CumulativeResult extends ToString {
    private static final long serialVersionUID = 1232776457102455894L;
    /**
     * 累计金额
     */
    private long              cumulativeAmount;
    /**
     * 累计人数
     */
    private Integer           cumulativeNumber;
}
