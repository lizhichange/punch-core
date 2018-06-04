package com.fulihui.punch.request;

import org.near.toolkit.model.ToString;

import com.fulihui.weixinclient.request.order.TransfersOrderWeixinRequest;

public class WeixinAmountRequest extends ToString{
    
    private TransfersOrderWeixinRequest request;
    
    private String  punchRecord;

    public TransfersOrderWeixinRequest getRequest() {
        return request;
    }

    public void setRequest(TransfersOrderWeixinRequest request) {
        this.request = request;
    }


    public String getPunchRecord() {
        return punchRecord;
    }

    public void setPunchRecord(String punchRecord) {
        this.punchRecord = punchRecord;
    }

    
    
    
    

}
