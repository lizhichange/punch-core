package com.fulihui.punch.core.zbus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fulihui.accountcore.enums.ProductType;
import com.fulihui.punch.biz.SendTemplateBiz;
import com.fulihui.punch.biz.message.processor.MessageProcessor;
import com.fulihui.punch.biz.message.processor.MessageProcessorRegister;
import com.fulihui.punch.biz.processor.UserAccountManager;
import com.fulihui.punch.common.config.AppConst;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.dal.dataobj.UserPunchCount;
import com.fulihui.punch.dal.dataobj.UserPunchCountExample;
import com.fulihui.punch.dal.mapper.UserPunchCountMapper;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.MessageTypeEnum;
import com.fulihui.punch.model.ZbusPunchModel;
import com.fulihui.punch.request.MessageRequest;
import com.fulihui.punch.request.WeixinAmountRequest;
import com.fulihui.weixinclient.request.order.TransfersOrderWeixinRequest;
import org.near.servicesupport.result.BaseResult;
import org.near.toolkit.common.Log;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zbus.broker.Broker;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Consumer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

import static com.fulihui.punch.core.zbus.Const.TAOBAOKE;
import static com.fulihui.punch.enums.TemplateTypeEnum.DAKA_SUCC;
import static org.near.toolkit.common.DateUtils.format;

/**
 * @author leeson 2016年5月6日 下午7:21:43
 * 短消息队列 消费者 （随容器启动）
 */
@Component
public class ZbusConsumerHandle {

    private static final Logger LOGGER          = LoggerFactory.getLogger(ZbusConsumerHandle.class);

    @Autowired
    private AppConst            appConst;
    @Autowired
    MessageProcessorRegister    messageProcessorRegister;
    @Autowired
    UserPunchCountMapper        userPunchCountMapper;
    @Autowired
    UserPunchRepository         userPunchRepository;

    private Broker              broker;

    @Autowired
    private UserAccountManager  userAccountManager;

    @Autowired
    SendTemplateBiz             sendTemplateBiz;

    private static final int    PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();

    @PostConstruct
    public void init() throws Exception {

        try {
            broker = new ZbusBroker(appConst.getZbusUrl());
            //生码消费者
            final Consumer c1 = new Consumer(broker, ProductType.YIYUAN_PUNCH.getCode());
            c1.setConsumerHandlerPoolSize(10);
            c1.setConsumerHandlerRunInPool(true);
            c1.start((message, consumer) -> {
                try {
                    String messageJson = message.getBodyString();
                    LOGGER.info("S<-Q:{}", JSON.parseObject(messageJson));
                    if (StringUtil.isNotEmpty(messageJson)) {

                        WeixinAmountRequest request = JSON.parseObject(messageJson,
                            WeixinAmountRequest.class);
                        TransfersOrderWeixinRequest weixinRequest = request.getRequest();
                        userAccountManager.invoke(weixinRequest, request.getPunchRecord());
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });

            final Consumer c5 = new Consumer(broker, TAOBAOKE);
            c5.setConsumerHandlerPoolSize(10);
            c5.setConsumerHandlerRunInPool(true);
            c5.start((message, consumer) -> {
                try {
                    String messageJson = message.getBodyString();
                    LOGGER.info("S<-Q:{}", JSON.parseObject(messageJson));
                    if (StringUtil.isNotEmpty(messageJson)) {
                        WeixinAmountRequest request = JSON.parseObject(messageJson,
                                WeixinAmountRequest.class);
                        TransfersOrderWeixinRequest weixinRequest = request.getRequest();
                        userAccountManager.invokeTK(weixinRequest, request.getPunchRecord());
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });

            final Consumer c = new Consumer(broker, Const.QUEUE_NAME);
            c.setConsumerHandlerPoolSize(10);
            c.setConsumerHandlerRunInPool(true);
            c.start((message, consumer) -> {
                try {
                    String messageJson = message.getBodyString();
                    LOGGER.info("S<-Q:{}", JSON.parseObject(messageJson));
                    if (StringUtil.isNotEmpty(messageJson)) {

                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });

            final Consumer c2 = new Consumer(broker, Const.PUSH_NOTIFY_MSG);
            c2.setConsumerHandlerPoolSize(10);
            c2.setConsumerHandlerRunInPool(true);
            c2.start((message, consumer) -> {
                try {
                    String messageJson = message.getBodyString();
                    MessageRequest request = JSON.parseObject(messageJson, MessageRequest.class);
                    LOGGER.info("S<-Q:{}", request);

                    MessageProcessor processor = messageProcessorRegister
                        .getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
                    if (processor != null) {
                        processor.sendMessage(request);
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        final Consumer c = new Consumer(broker, Const.USER_TEMPLATE);
        c.setConsumerHandlerPoolSize(10);
        c.setConsumerHandlerRunInPool(true);
        c.start((message, consumer) -> {
            try {
                sendTemplateBiz.sendTemplateMsg(
                    JSON.parseObject(message.getBodyString(), MessageRequest.class));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        });

    }

    //fixme 打卡提醒 下线
    /**
     * 成功打卡提醒
     *
     * @param messageJson
     */
    @Deprecated
    private void punchPush(String messageJson) {
        ZbusPunchModel model = JSONObject.parseObject(messageJson, ZbusPunchModel.class);
        if (model != null) {
            UserPunchRecordDTO dto = userPunchRepository.queryByPunchOrderId(model.getUserId(),
                model.getPunchOrderId());
            if (dto != null) {
                push(dto);
            }
        }
    }

    private void push(UserPunchRecordDTO dto) {

        MessageProcessor processor = messageProcessorRegister
            .getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
        if (processor != null) {
            UserPunchCountExample example = new UserPunchCountExample();
            example.createCriteria().andUserIdEqualTo(dto.getUserId());

            List<UserPunchCount> userPunchCounts = userPunchCountMapper.selectByExample(example);

            MessageRequest message = new MessageRequest();
            message.setOpenId(dto.getOpenId());
            message.setChannel(DAKA_SUCC.getCode());

            message.setFirst(format(dto.getPeriodDate(), " yyyy年MM月dd日") + "打卡成功！\n"
                             + "奖励将在   10:00后结算，并在24小时内发放到您的微信钱包中，请您及时关注。\n" + "您已连续打卡"
                             + userPunchCounts.get(0).getContinuousCount() + "天，坚持打卡才是胜利！\n");
            message.setKeyword1("福礼惠天天打卡");
            message.setKeyword2(format(dto.getPunchDate(), " yyyy年MM月dd日 HH:mm:ss"));
            message.setRemark("\n ➜  开启新的挑战");
            BaseResult baseResult = processor.sendMessage(message);
            LOGGER.info("baseResult:{},openid{}", baseResult, dto.getOpenId());
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            broker.close();
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
    }
}