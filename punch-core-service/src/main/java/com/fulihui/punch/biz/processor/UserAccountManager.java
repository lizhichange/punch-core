package com.fulihui.punch.biz.processor;

import com.fulihui.weixinclient.request.order.TransfersOrderWeixinRequest;


public interface UserAccountManager {
    
    
    
    void invoke(TransfersOrderWeixinRequest request,String punchRecordId);

    void invokeTK(TransfersOrderWeixinRequest request,String punchRecordId);
    
}
