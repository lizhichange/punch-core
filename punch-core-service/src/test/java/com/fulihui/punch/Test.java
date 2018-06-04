package com.fulihui.punch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fulihui.weixinclient.model.TemplateData;

import java.util.Map;

/**
 * Created by 1 on 2017/12/28.
 */
public class Test {

    public static void main(String[] args) {
        String messageBody = "{\"first\":{\"color\":\"#173177\",\"value\":\"您好，你有一个现金红包未领取\"},\"keyword1\":{\"color\":\"#173177\",\"value\":\"理财通投资1分钱即可拿现金红包，最高10元，红包即刻到账至微信钱包。\"},\"keyword2\":{\"value\":\"2016-11-18 13:32:01\"},\"remark\":{\"value\":\"进入详情，点击“已参与”按钮，投资理财通即可。投资金额可提现，还有现金收益哦。\"}}";
        Map<String, TemplateData> data = JSON.parseObject(messageBody,
                new TypeReference<Map<String, TemplateData>>() {});
        System.out.println(data);
    }
}
