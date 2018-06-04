package com.fulihui.punch.api;

import org.near.servicesupport.result.BaseResult;

import com.fulihui.punch.request.MessageRequest;

/**
 * Created by 1 on 2017/12/28.
 */
public interface WeiXinTemplateService {

    BaseResult sendSingleMsg(MessageRequest request);

}