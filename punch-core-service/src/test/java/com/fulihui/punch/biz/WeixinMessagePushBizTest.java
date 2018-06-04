package com.fulihui.punch.biz;


import com.fulihui.punch.AbstractToStringSupport;
import com.fulihui.punch.biz.message.WeixinMessagePushBiz;
import com.fulihui.punch.biz.message.processor.MessageProcessor;
import com.fulihui.punch.biz.message.processor.MessageProcessorRegister;
import com.fulihui.punch.enums.MessageTypeEnum;
import com.fulihui.punch.enums.TemplateTypeEnum;
import com.fulihui.punch.request.MessageRequest;
import org.junit.Test;
import org.near.servicesupport.result.BaseResult;
import org.near.toolkit.common.DateUtils;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by 1 on 2017/12/28.
 */

@ContextConfiguration(locations = {"classpath:spring/test-dubbo-consumer.xml","classpath:spring/test-root-context.xml", "classpath:spring/test-repository-context.xml",
        "classpath:spring/test-datasource-context.xml" })
public class WeixinMessagePushBizTest extends AbstractToStringSupport {

    @Resource
    WeixinMessagePushBiz weixinMessagePushBiz;

    @Resource
    MessageProcessorRegister messageProcessorRegister;

    @Test
    public void test_sendMessage(){
        MessageProcessor processor = messageProcessorRegister.getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setChannel(TemplateTypeEnum.DAKA_SUCC.getCode());
        messageRequest.setOpenId("o83KpwMKfM1sBsq-1MGx5xs0XhbM");
        messageRequest.setFirst("打卡成功通知");
        messageRequest.setKeyword1("1月2日打卡成功！\n" +
                "奖励将在10:00后结算，并在24小时内发放到您的微信钱包中，请您及时关注。\n" +
                "您已连续打卡1天，坚持打卡才是胜利！");
        messageRequest.setKeyword2("福礼惠打卡挑战");
        messageRequest.setRemark("2018年1月2日 08:30:20");
        BaseResult baseResult =  processor.sendMessage(messageRequest);
        System.out.println(baseResult);
    }

    @Test
    public void test_sendMessageDAKA_SIGN(){
        MessageProcessor processor = messageProcessorRegister.getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setChannel(TemplateTypeEnum.DAKA_SIGN.getCode());
        messageRequest.setOpenId("o83KpwMKfM1sBsq-1MGx5xs0XhbM");
        messageRequest.setFirst("\n" +
                "挑战已激活！\n" +
                "请于1月2日8:00-10:00完成打卡，瓜分挑战金！\n" +
                "注意：因央行要求，收取奖励必须完成微信支付实名认证。如长时间未收到奖励，请检查下自己是否认证过哟~");
        messageRequest.setKeyword1("1月2日打卡成功！\n" +
                "奖励将在10:00后结算，并在24小时内发放到您的微信钱包中，请您及时关注。\n" +
                "您已连续打卡1天，坚持打卡才是胜利！");
        messageRequest.setKeyword2("福礼惠打卡挑战");
        messageRequest.setRemark("2018年1月2日 08:30:20");
        BaseResult baseResult =  processor.sendMessage(messageRequest);
        System.out.println(baseResult);
    }

    @Test
    public void test_sendMessageDAKA_TIXING(){
        MessageProcessor processor = messageProcessorRegister.getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
        MessageRequest messageRequest = new MessageRequest();
       messageRequest.setChannel(TemplateTypeEnum.DAKA_TIXING.getCode());
         messageRequest.setOpenId("o83KpwMKfM1sBsq-1MGx5xs0XhbM");
        messageRequest.setChannel(TemplateTypeEnum.DAKA_TIXING.getCode());
        messageRequest.setFirst("铃铃铃！开始打卡啦！\n" +
                "，请在8:00到10:00前完成打卡，千万别错过咯。");
        messageRequest.setKeyword1("福礼惠打卡挑战");
        messageRequest.setKeyword2("开始打卡");
        messageRequest.setKeyword3(DateUtils.format(new Date(),"yyyy年MM月dd日 HH:mm:ss"));
        messageRequest.setRemark("\n ➜  马上去打卡");
        BaseResult baseResult =  processor.sendMessage(messageRequest);
        System.out.println(baseResult);
    }

    @Test
    public void test_sendProcessor(){
        MessageProcessor processor = messageProcessorRegister.getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setChannel("DAKA_SIGN");
        messageRequest.setOpenId("o83KpwE_qNjxDWeu9I9_0tRrttAM");
        messageRequest.setFirst("头");
        messageRequest.setRemark("尾");
        messageRequest.setKeyword1("内容1");
        messageRequest.setKeyword2("内容2");
        processor.sendMessage(messageRequest);
    }

}
