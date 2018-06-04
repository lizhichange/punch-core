package com.fulihui.punch.core.zbus;

import static com.fulihui.punch.core.zbus.Const.TAOBAOKE;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zbus.broker.Broker;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Producer;
import org.zbus.net.http.Message;

import com.alibaba.fastjson.JSON;
import com.fulihui.accountcore.enums.ProductType;
import com.fulihui.punch.common.config.AppConst;

/**
 * @author leeson 2016年5月8日 下午12:37:57
 */
@Component
public class ZbusProducerHandle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZbusProducerHandle.class);
    @Autowired
    private AppConst            appConst;

    private Broker              broker;

    private Producer            dataProcessProducer;

    private Producer            producerWithdrawal;

    private Producer            templateProducer;

    private Producer            pushNotifyProducer;

    private Producer            taoProducer;

    @PostConstruct
    public void init() throws Exception {
        broker = new ZbusBroker(appConst.getZbusUrl());
        dataProcessProducer = new Producer(broker, Const.QUEUE_NAME);
        dataProcessProducer.createMQ();

        producerWithdrawal = new Producer(broker, ProductType.YIYUAN_PUNCH.getCode());
        producerWithdrawal.createMQ();

        templateProducer = new Producer(broker, Const.USER_TEMPLATE);
        templateProducer.createMQ();

        pushNotifyProducer = new Producer(broker, Const.PUSH_NOTIFY_MSG);
        pushNotifyProducer.createMQ();

        taoProducer = new Producer(broker, Const.TAOBAOKE);
        taoProducer.createMQ();
    }

    /**
     * 发送消息到消息队列
     *
     * @param messageJson
     * @return
     */
    public boolean commitToMQ(String messageJson, String queueName) {
        Message msg = new Message(messageJson);
        LOGGER.info("S->Q:{},queueName:{}", JSON.parseObject(messageJson), queueName);
        try {
            if (StringUtil.equals(Const.QUEUE_NAME, queueName)) {
                dataProcessProducer.sendSync(msg);
            }
            if (StringUtil.equals(ProductType.YIYUAN_PUNCH.getCode(), queueName)) {
                Message sendSync = producerWithdrawal.sendSync(msg);
            }

            if (StringUtil.equals(TAOBAOKE, queueName)) {
                Message sendSync = taoProducer.sendSync(msg);
            }

            if (StringUtil.equals(Const.USER_TEMPLATE, queueName)) {
                templateProducer.sendAsync(msg);
            }

            if (StringUtil.equals(Const.PUSH_NOTIFY_MSG, queueName)) {
                pushNotifyProducer.sendAsync(msg);
            }

            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            broker.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}