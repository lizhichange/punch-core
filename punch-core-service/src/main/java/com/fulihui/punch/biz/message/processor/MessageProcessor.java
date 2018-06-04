package com.fulihui.punch.biz.message.processor;

import org.near.servicesupport.result.BaseResult;

import com.fulihui.punch.enums.MessageTypeEnum;
import com.fulihui.punch.request.MessageRequest;

/**
 * Created by 1 on 2017/12/28.
 */
public interface MessageProcessor {

    /**
     * 发送消息
     * @param request @link MessageRequest
     * @return
     */
    BaseResult sendMessage(MessageRequest request);

    /**
     *
     * @return
     */
    MessageTypeEnum type();
}
