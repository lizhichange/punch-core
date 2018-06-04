package com.fulihui.punch.service;

import static com.fulihui.punch.enums.TemplateTypeEnum.DAKA_SUCC;
import static org.near.toolkit.common.DateUtils.format;

import java.util.Date;

import org.near.servicesupport.result.BaseResult;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.util.ServiceResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fulihui.punch.api.UserPunchService;
import com.fulihui.punch.api.WeiXinTemplateService;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.request.MessageRequest;
import com.fulihui.punch.request.UserPunchUserIdRequest;

/**
 * Created by  lz on 2017-7-10.
 */
public class UserPunchServiceTest extends AbstractServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPunchServiceTest.class);

    @Reference(version = "1.0.0")
    UserPunchService            userPunchService;

    @Reference(version = "1.0.0")
    WeiXinTemplateService       weiXinTemplateService;

    @Test
    public void queryByUserId() throws Exception {
        UserPunchUserIdRequest request = new UserPunchUserIdRequest();
        request.setUserId("111");
        TPageResult<UserPunchRecordDTO> result = userPunchService.queryPage(request);
        ServiceResultUtil.checkResult(result);
        LOGGER.info("result:{}", result);
    }

    @Test
    public void weiXinTemplateService() throws Exception {

        MessageRequest message = new MessageRequest();
        message.setOpenId("ozpePv4n-cJzF0dVaHXIjqZPuPIo");
        message.setChannel(DAKA_SUCC.getCode());
        message.setFirst(format(new Date(), " yyyy年MM月dd日") + "打卡成功！\n"
                         + "奖励将在   10:00后结算，并在24小时内发放到您的微信钱包中，请您及时关注。\n" + "您已连续打卡" + 1
                         + "天，坚持打卡才是胜利！\n");
        message.setKeyword1("福礼惠打卡挑战");
        message.setKeyword2(format(new Date(), " yyyy年MM月dd日 HH:mm:ss"));
        message.setRemark("\n ➜  开启新的挑战");

        BaseResult baseResult = weiXinTemplateService.sendSingleMsg(message);
        LOGGER.info("baseResult:{}", baseResult);
    }
}
