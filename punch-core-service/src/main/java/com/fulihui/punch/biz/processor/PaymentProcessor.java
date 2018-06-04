package com.fulihui.punch.biz.processor;

import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderPayTypeEnum;
import com.fulihui.punch.request.UserPunchCreateRequest;

/**
 * 订单付款处理类抽象接口
 *
 * @author LZ
 *  
 */
public interface PaymentProcessor {

    /**
     * 完成付款执行打卡记录
     * @param dto
     * @return 订单号
     */
    String payment(UserPunchRecordDTO dto,UserPunchCreateRequest request);

    /**
     * 获取处理类对应的付款类型
     * @return    OrderPayTypeEnum
     */
    OrderPayTypeEnum getPayType();

}
