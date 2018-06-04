package com.fulihui.punch.service;

import org.near.servicesupport.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.WeiXinTemplateService;
import com.fulihui.punch.biz.message.processor.MessageProcessor;
import com.fulihui.punch.biz.message.processor.MessageProcessorRegister;
import com.fulihui.punch.enums.MessageTypeEnum;
import com.fulihui.punch.request.MessageRequest;

@Service("weiXinTemplateService")
public class WeiXinTemplateServiceImpl implements WeiXinTemplateService {

    @Autowired
    MessageProcessorRegister messageProcessorRegister;

    @Override
    public BaseResult sendSingleMsg(MessageRequest request) {
        MessageProcessor processor = messageProcessorRegister
            .getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
        return processor.sendMessage(request);

    }
}
