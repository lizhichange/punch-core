package com.fulihui.punch.biz.message.processor.impl;

import static org.near.servicesupport.result.ResultBuilder.fail;
import static org.near.servicesupport.result.ResultBuilder.succ;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.near.servicesupport.result.BaseResult;
import org.near.toolkit.common.DateUtils;
import org.near.toolkit.common.EnumUtil;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fulihui.punch.api.PunchWechatTokenService;
import com.fulihui.punch.biz.integration.WechatTokenServiceClient;
import com.fulihui.punch.biz.message.WeixinMessagePushBiz;
import com.fulihui.punch.biz.message.processor.MessageTemplateCache;
import com.fulihui.punch.common.config.AppConst;
import com.fulihui.punch.dal.dataobj.PunchTemplateMsg;
import com.fulihui.punch.dal.dataobj.PunchTemplateMsgExample;
import com.fulihui.punch.dal.mapper.PunchTemplateMsgMapper;
import com.fulihui.punch.enums.TemplateTypeEnum;
import com.fulihui.punch.request.MessageRequest;
import com.fulihui.punch.request.WechatTokenQueryRequest;
import com.fulihui.punch.util.WechatConfigFactory;
import com.fulihui.systemcore.dto.WechatConfig;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.model.TemplateData;
import com.fulihui.weixinclient.model.TemplateMessage;
import com.fulihui.weixinclient.request.TemplateMessageWeixinRequest;
import com.fulihui.weixinclient.result.WeixinJsonResult;

/**
 * Created by 1 on 2017/12/28.
 */
@Component
public class WeixinMessagePushBizImpl implements WeixinMessagePushBiz {

    private static final Logger      LOGGER   = LoggerFactory
        .getLogger(WeixinMessagePushBizImpl.class);

    @Autowired
    PunchTemplateMsgMapper           punchTemplateMsgMapper;

    @Autowired
    WechatTokenServiceClient         wechatTokenServiceClient;

    @Autowired
    PunchWechatTokenService          punchWechatTokenService;

    @Autowired

    AppConst                         appConst;

    @Autowired
    WeixinClient                     weixinClient;

    @Autowired
    WechatConfigFactory              wechatConfigFactory;

    private ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1,
        new BasicThreadFactory.Builder().namingPattern("weixinmessagepushbiz-schedule-pool-%d")
            .daemon(true).build());

    @PreDestroy
    public void destroy() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    @PostConstruct
    public void init() {
        executor.scheduleAtFixedRate(() -> {
            try {
                refresh();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }, 0, 2, TimeUnit.MINUTES);
    }

    void refresh() {
        List<PunchTemplateMsg> list = punchTemplateMsgMapper
            .selectByExample(new PunchTemplateMsgExample());
        if (isEmpty(list)) {
            LOGGER.warn("No PunchTemplateMsg can be used.");
            MessageTemplateCache.clear();
            return;
        }
        Map<String, PunchTemplateMsg> map = list.stream().collect(Collectors
            .toMap(PunchTemplateMsg::getChannle, templateMsg -> templateMsg, (k, v) -> v));
        MessageTemplateCache.set(map);
    }

    @Override
    public BaseResult pushTemplateMsg(MessageRequest request) {

        String openId = request.getOpenId();
        if (StringUtil.isBlank(openId)) {
            LOGGER.warn("openId不能为空");
            return fail(500, "openId不能为空");
        }
        String channel = request.getChannel();
        if (StringUtil.isBlank(channel)) {
            LOGGER.warn("channel不能为空");
            return fail(500, "channel不能为空");
        }

        WechatConfig wechatConfig = wechatConfigFactory.getYouFuLi();

        WechatTokenQueryRequest tokenQueryRequest = new WechatTokenQueryRequest();
        tokenQueryRequest.setAppid(wechatConfig.getAppID());
        tokenQueryRequest.setAppsecret(wechatConfig.getAppsecret());

        String token = punchWechatTokenService.takeAccessToken(tokenQueryRequest);
        if (StringUtil.isBlank(token)) {
            LOGGER.warn("token获取失败");
            return fail(500, "token获取失败!");
        }
        PunchTemplateMsg punchTemplateMsg = MessageTemplateCache.get(channel);
        if (punchTemplateMsg == null) {
            LOGGER.warn("模板消息获取失败,模版渠道类型,channel:{}", channel);
            return fail(500, "模板消息获取失败");
        }
        replaceStr(punchTemplateMsg, request);
        TemplateMessageWeixinRequest templateRequest = getWeiXinMsgTemplate(token, openId,
            punchTemplateMsg);
        LOGGER.debug("参数:{}", punchTemplateMsg.toString());
        LOGGER.debug("模板信息:{}", punchTemplateMsg.getMessageBody());
        WeixinJsonResult invokeResult = weixinClient.invokeService(templateRequest);
        TemplateTypeEnum typeEnum = EnumUtil.queryByCode(channel, TemplateTypeEnum.class);
        LOGGER.info("一元打卡,用户openId:{},模板推送返回结果{},模版类型:{}", openId, invokeResult,
            typeEnum != null ? typeEnum.getDesc() : "");
        String code = "0";
        if (StringUtil.equals(code, invokeResult.getErrcode())) {
            return succ();
        }
        return fail(Integer.parseInt(invokeResult.getErrcode()), invokeResult.getErrmsg());
    }

    /**
     * 替换字符串
     *
     * @param msg
     */
    public void replaceStr(PunchTemplateMsg msg, MessageRequest request) {
        Class cls = request.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            String value = null;
            for (Field field : fields) {
                field.setAccessible(true);
                if ("SERIALVERSIONUID".equals(field.getName().toUpperCase())) {
                    continue;
                }
                value = (String) field.get(request);
                if (Objects.nonNull(value)) {
                    msg.setMessageBody(
                        msg.getMessageBody().replace("{{" + field.getName() + ".DATA}}", value));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private TemplateMessageWeixinRequest getWeiXinMsgTemplate(String accessToken, String openId,
                                                              PunchTemplateMsg punchTemplateMsg) {
        TemplateMessageWeixinRequest request = new TemplateMessageWeixinRequest();
        request.setAccess_token(accessToken);
        try {
            //组装微信模板
            String templeId = punchTemplateMsg.getTemplateId();
            String url = punchTemplateMsg.getMessageLink();
            String messageBody = punchTemplateMsg.getMessageBody();
            //特殊处理时间，将NOW替换为当前时间
            String nowDate = DateUtils.formatChineseDtFormat(new Date());
            messageBody = messageBody.replaceAll("NOW", nowDate);
            LOGGER.info("messageBody:{}", messageBody);
            //发送给
            TemplateMessage templateMessage = new TemplateMessage();
            templateMessage.setTouser(openId);
            templateMessage.setTemplate_id(templeId);
            templateMessage.setUrl(url);
            Map<String, TemplateData> data = JSON.parseObject(messageBody,
                new TypeReference<Map<String, TemplateData>>() {
                });
            templateMessage.setData(data);
            String body = JSON.toJSONString(templateMessage);
            request.setBody(body);
            return request;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
