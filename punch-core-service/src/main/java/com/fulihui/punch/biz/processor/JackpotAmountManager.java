package com.fulihui.punch.biz.processor;

import com.fulihui.punch.dto.UserPunchRecordDTO;

/**
 * 奖池金额
 *
 * @author lz
 */
public interface JackpotAmountManager {
    /**
     * Take.
     *
     * @param dto the dto
     */
    void take(UserPunchRecordDTO dto);
}
