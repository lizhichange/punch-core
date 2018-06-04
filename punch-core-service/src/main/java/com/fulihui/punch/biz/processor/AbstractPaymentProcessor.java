package com.fulihui.punch.biz.processor;

import org.springframework.beans.factory.annotation.Autowired;

import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.dto.UserPunchRecordDTO;

/**
 *
 * @author lz
 */
public abstract class AbstractPaymentProcessor implements PaymentProcessor {

    @Autowired
    protected UserPunchRepository userPunchRepository;

    public void commonCheck(UserPunchRecordDTO dto) throws Exception {

    }
}
