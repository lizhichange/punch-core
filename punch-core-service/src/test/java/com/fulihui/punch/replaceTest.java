package com.fulihui.punch;

import com.fulihui.punch.dal.dataobj.PunchTemplateMsg;
import com.fulihui.punch.request.MessageRequest;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Created by 1 on 2018/1/5.
 */
public class replaceTest {

    public static void main(String[] args) {

        PunchTemplateMsg msg=new PunchTemplateMsg();
        msg.setMessageBody("{\n" +
                "    \"first\": {\n" +
                "        \"color\": \"#000000\",\n" +
                "        \"value\": \"{{first.DATA}}\"\n" +
                "    },\n" +
                "    \"keyword1\": {\n" +
                "        \"color\": \"#000000\",\n" +
                "        \"value\": \"{{keyword1.DATA}}\"\n" +
                "    },\n" +
                "    \"keyword2\": {\n" +
                "        \"color\": \"#000000\",\n" +
                "        \"value\": \"{{keyword2.DATA}}\"\n" +
                "    },\n" +
                "    \"remark\": {\n" +
                "        \"color\": \"#000000\",\n" +
                "        \"value\": \"{{remark.DATA}}\"\n" +
                "    }\n" +
                "}");
        MessageRequest request=new MessageRequest();
        request.setFirst("1");
        request.setKeyword1("2");
        request.setKeyword2("3");
        request.setRemark("4");
        request.setChannel("DAKA_SIGN");
/*        msg.setMessageBody(msg.getMessageBody().replace("{{first.DATA}}",request.getFirst()));
        msg.setMessageBody(msg.getMessageBody().replace("{{keyword1.DATA}}",request.getKeyWord1()));
        msg.setMessageBody(msg.getMessageBody().replace("{{keyword2.DATA}}",request.getKeyWord2()));
        msg.setMessageBody(msg.getMessageBody().replace("{{remark.DATA}}",request.getRemark()));*/



        replaceTest replaceTest=new replaceTest();
        replaceTest.replaceStr(msg,request);
        //msg.getMessageBody().replace("key",null);
        System.out.println(msg.getMessageBody());


    }


    public void replaceStr(PunchTemplateMsg msg, MessageRequest request) {
        Class cls = request.getClass();
        Field[] fields= cls.getDeclaredFields();
        try
        {
            String value = null;
            for (Field field:fields)
            {
                field.setAccessible(true);
                value = (String) field.get(request);
                if (Objects.nonNull(value))
                {
                    System.out.println(value);
                    msg.setMessageBody(msg.getMessageBody().replace("{{"+field.getName()+".DATA}}", value));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
