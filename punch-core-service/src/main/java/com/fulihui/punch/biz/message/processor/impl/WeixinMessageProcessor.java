package com.fulihui.punch.biz.message.processor.impl;

import com.fulihui.punch.biz.message.WeixinMessagePushBiz;
import com.fulihui.punch.biz.message.processor.MessageProcessor;
import com.fulihui.punch.enums.MessageTypeEnum;
import com.fulihui.punch.request.MessageRequest;
import org.near.servicesupport.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 1 on 2017/12/28.
 */
@Component
public class WeixinMessageProcessor implements MessageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(WeixinMessageProcessor.class);

    @Autowired
    WeixinMessagePushBiz weixinMessagePushBiz;

    @Override
    public BaseResult sendMessage(MessageRequest request) {
        return weixinMessagePushBiz.pushTemplateMsg(request);
    }

    @Override
    public MessageTypeEnum type() {
        return MessageTypeEnum.WEIXIN_MESSAGE;
    }
}
