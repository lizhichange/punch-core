package com.fulihui.punch.biz.processor;

import com.fulihui.punch.enums.OrderPayTypeEnum;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支付处理类注册器
 * Created by willa on 2016/1/11.
 */
@Component
public class PaymentProcessorRegister extends ApplicationObjectSupport {

    private Map<OrderPayTypeEnum, PaymentProcessor> register = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Map<String, PaymentProcessor> processors = getApplicationContext()
            .getBeansOfType(PaymentProcessor.class);
        for (PaymentProcessor processor : processors.values()) {
            register.put(processor.getPayType(), processor);
        }
    }

    /**
     * clear
     */
    @PreDestroy
    public void clear() {
        register.clear();
    }

    /**
     * @param payType
     * @return       PaymentProcessor
     */
    public PaymentProcessor getProcessor(OrderPayTypeEnum payType) {
        return register.get(payType);
    }

}
