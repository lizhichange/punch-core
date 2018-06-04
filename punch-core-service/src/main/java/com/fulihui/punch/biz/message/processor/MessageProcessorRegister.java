package com.fulihui.punch.biz.message.processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import com.fulihui.punch.enums.MessageTypeEnum;

/**
 * Created by 1 on 2017/12/28.
 */
@Component
public class MessageProcessorRegister extends ApplicationObjectSupport {

    private Map<MessageTypeEnum, MessageProcessor> register = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Map<String, MessageProcessor> processors = getApplicationContext()
            .getBeansOfType(MessageProcessor.class);
        for (MessageProcessor processor : processors.values()) {
            register.put(processor.type(), processor);
        }
    }

    public MessageProcessor getProcessor(MessageTypeEnum typeEnum) {
        return register.get(typeEnum);
    }

    /**
     * clear
     */
    @PreDestroy
    public void clear() {
        register.clear();
    }
}
