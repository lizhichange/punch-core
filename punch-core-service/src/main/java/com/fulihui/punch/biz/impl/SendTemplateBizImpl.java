package com.fulihui.punch.biz.impl;

import com.fulihui.punch.biz.SendTemplateBiz;
import com.fulihui.punch.biz.message.processor.MessageProcessor;
import com.fulihui.punch.biz.message.processor.MessageProcessorRegister;
import com.fulihui.punch.enums.MessageTypeEnum;
import com.fulihui.punch.request.MessageRequest;
import org.near.servicesupport.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 *
 * @author 1
 * @date 2018/1/18
 */
@Component
public class SendTemplateBizImpl  implements SendTemplateBiz {
    private static final Logger logger          = LoggerFactory.getLogger(SendTemplateBizImpl.class);

    @Autowired
    MessageProcessorRegister register;

    @Override
    public void sendTemplateMsg(MessageRequest request) {
        if(Objects.isNull(request)){
            logger.error("sendTemplateMsg request is null,data={}",request);
            return;
        }
        try{
            MessageProcessor processor = register.getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
            BaseResult result = processor.sendMessage(request);
            if(result.getErrcode()!=0){
                logger.error("sendTemplateMsg send error,result={}",request);
            }
        }catch (Exception e){
            logger.error("sendTemplateMsg error,data={}",request);
        }

    }
}
