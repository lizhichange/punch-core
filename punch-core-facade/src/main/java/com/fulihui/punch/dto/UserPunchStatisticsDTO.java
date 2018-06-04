package com.fulihui.punch.dto;

import lombok.Getter;
import lombok.Setter;
import org.near.toolkit.model.ToString;

import java.util.Date;

/**
 * @author ssn
 */
@Setter
@Getter
public class UserPunchStatisticsDTO extends ToString {
    private static final long serialVersionUID = -7790297806881420878L;
    /**
     * 主键
     */

    private Integer id;

    /**
     * 支付金额
     */
    private Long payAmount;

    /**
     * 成功打卡人数
     */

    private Long successNum;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 期号
     */
    private Date periodDate;

    /**
     * 每个人发放金额
     */

    private Long payOneAmount;

    /**
     * 特殊补贴金额
     */
    private Long subsidyAmount;

    /**
     * 总共发放金额
     */

    private Long totalAmount;

    /**
     * 后台设置补贴金额
     */

    private Long subsidySetupAmount;

    /**
     * 打卡失败人数
     */

    private Long failNum;

    /**
     * 总参与人数
     */

    private Long partakeNum;


}