package com.fulihui.punch.zbus;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.zbus.broker.Broker;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Producer;
import org.zbus.net.http.Message;

import com.alibaba.fastjson.JSONObject;
import com.fulihui.accountcore.enums.ProductType;
import com.fulihui.weixinclient.request.order.TransfersOrderWeixinRequest;

/**
 * Created by  lz on 2017-6-16.
 */
public class ZbusTest {

    private String mqAddress = "192.168.1.51:15555";

    @Test
    public void test01() {

        try (Broker broker = new ZbusBroker(mqAddress)) {
            Producer producer = new Producer(broker, ProductType.YIYUAN_PUNCH.getCode());
            Message msg = new Message();
            TransfersOrderWeixinRequest request = new TransfersOrderWeixinRequest();
            msg.setBody(JSONObject.toJSONString(request));
            producer.sendAsync(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 }
