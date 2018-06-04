package com.fulihui.punch.biz.message;

import com.fulihui.punch.request.MessageRequest;
import org.near.servicesupport.result.BaseResult;

/**
 * 微信消息推送业务接口
 * Created by 1 on 2017/12/28.
 */
public interface WeixinMessagePushBiz {

    /**
     *
     * @param request {@link com.fulihui.punch.enums.TemplateTypeEnum}
     * @return
     */
    BaseResult pushTemplateMsg(MessageRequest request);
}
