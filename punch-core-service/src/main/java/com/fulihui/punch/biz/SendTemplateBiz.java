package com.fulihui.punch.biz;

import com.fulihui.punch.request.MessageRequest;

/**
 *
 * @author 1
 * @date 2018/1/18
 */
public interface SendTemplateBiz {

    /***
     * 发送模板消息
     * @param request
     */
    void sendTemplateMsg (MessageRequest request);
}
