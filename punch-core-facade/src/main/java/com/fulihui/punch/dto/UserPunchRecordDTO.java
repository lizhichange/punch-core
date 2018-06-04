package com.fulihui.punch.dto;

import java.util.Date;

import org.near.toolkit.model.ToString;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lz
 */
@Setter
@Getter
public class UserPunchRecordDTO extends ToString {
    private static final long serialVersionUID = -2585072736063357200L;

    /**
     * 订单id
     */
    private String            punchOrderId;
    /**
     * 用户id
     */
    private String            userId;
    /**
     * 外部交易号
     */
    private String            outTradeNo;
    /**
     * 打开签到时间
     */
    private Date              punchDate;
    /**
     * 状态
     */
    private String            status;
    /**
     * 支付时间
     */
    private Date              payDate;
    /**
     * 支付类型
     */
    private String            payType;

    /**
     * openId
     */
    private String            openId;

    /**
     * 渠道
     * 上层业务方自定义
     */
    private String            openIdChannel;

    /**
     * 支付描述
     */

    private String            payDecs;
    /**
     * 支付金额 分为单位
     */
    private Integer           payAmount;

    /**
     * 创建时间
     */
    private Date              gmtCreate;
    /**
     * 修改时间
     */
    private Date              gmtModified;
    /**
     * 打卡开始时间
     */
    private Date              pushStartDate;
    /**
     * 打卡结束时间
     *
     */
    private Date              pushEndDate;

    /**
     * 打卡期号时间
     *
     */
    private Date              periodDate;
    /**
     * 支付时间扩展信息
     *
     */
    private Date              payExtDate;

    /**
     * 返利金额
     */
    private Integer           rebateAmount;
}
