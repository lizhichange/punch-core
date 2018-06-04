package com.fulihui.punch.dal.mapper;

import static com.fulihui.punch.enums.OrderStatusEnum.WAIT_PAY_SUCCESS;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.near.toolkit.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fulihui.punch.AbstractToStringSupport;
import com.fulihui.punch.dal.dataobj.*;
import com.fulihui.punch.enums.OrderPayTypeEnum;

@ContextConfiguration(locations = { "classpath:spring/test-datasource-context.xml" })

public class UserPunchRecordMapperTest extends AbstractToStringSupport {
    @Autowired
    UserPunchRecordMapper     userPunchRecordMapper;

    @Autowired
    TUserDetailMapper         tUserDetailMapper;

    @Autowired
    UserPunchAmountMapper     userPunchAmountMapper;

    @Autowired
    WechatAuthDOMapper        wechatAuthDOMapper;

    @Autowired
    WechatTokenDOMapper       wechatTokenDOMapper;

    @Autowired
    UserPunchCountMapper      userPunchCountMapper;

    @Autowired
    UserPunchStatisticsMapper userPunchStatisticsMapper;

    @Autowired
    PunchJackpotAmountMapper  punchJackpotAmountMapper;

    @Autowired
    PunchSubsidyInfoMapper    punchSubsidyInfoMapper;

    @Test
    public void query() {
        UserPunchRecordExample exampleCount = new UserPunchRecordExample();
        exampleCount.createCriteria().andStatusEqualTo("WAIT_ALREADY");
        long count = userPunchRecordMapper.countByExample(exampleCount);
        System.out.println(count);
        int rows = 7;
        int page = (int) count / rows;

        for (int i = 1; i <= page + 1; i++) {
            int offset = i > 1 ? (i - 1) * rows : 0;
            UserPunchRecordExample example = new UserPunchRecordExample();
            UserPunchRecordExample.Criteria criteria = example.createCriteria();
            example.createCriteria().andStatusEqualTo("WAIT_ALREADY");
            example.setOffset(offset);
            example.setLimit(rows);
            example.setOrderByClause("period_date DESC , pay_date DESC");
            List<UserPunchRecord> punchRecords = userPunchRecordMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(punchRecords)) {
                punchRecords.forEach(it -> {
                    System.out.println(it.getPunchOrderId());
                });
                System.out.println(punchRecords.size());
            }
        }

    }

    @Test
    public void select() throws ParseException {
        UserPunchRecordExample example = new UserPunchRecordExample();

        Date date = DateUtils.parseWebFormat("2018-03-07");
        example.createCriteria().andPeriodDateEqualTo(date)
            .andStatusEqualTo(WAIT_PAY_SUCCESS.getCode());
        List<UserPunchRecord> records = userPunchRecordMapper.selectByExample(example);

    }

    @Test
    public void group() throws ParseException {

        List<TaoKeRecord> records = extTaoKeRecordMapper.group(new Date(),DateUtils.addDays(new Date(),1),1,2);
        System.out.println(records.size());
        int count = extTaoKeRecordMapper.count(new Date(), DateUtils.addDays(new Date(), 1));
        System.out.println(count);
    }
    @Autowired
    ExtTaoKeRecordMapper extTaoKeRecordMapper;

    //@Test
    public void delete() {
        userPunchRecordMapper.deleteByExample(new UserPunchRecordExample());
        tUserDetailMapper.deleteByExample(new TUserDetailExample());
        userPunchAmountMapper.deleteByExample(new UserPunchAmountExample());
        wechatAuthDOMapper.deleteByExample(new WechatAuthDOExample());
        wechatTokenDOMapper.deleteByExample(new WechatTokenDOExample());
        userPunchStatisticsMapper.deleteByExample(new UserPunchStatisticsExample());

        punchJackpotAmountMapper.deleteByExample(new PunchJackpotAmountExample());
        punchSubsidyInfoMapper.deleteByExample(new PunchSubsidyInfoExample());
        userPunchCountMapper.deleteByExample(new UserPunchCountExample());

    }

    //@Test
    public void insert() {
        Date date = new Date();

        UserPunchRecord record = new UserPunchRecord();
        record.setGmtCreate(date);
        record.setGmtModified(date);

        record.setUserId(UUID.randomUUID().toString());
        record.setPunchOrderId(UUID.randomUUID().toString());
        record.setOpenId(UUID.randomUUID().toString());
        record.setPayAmount(100);

        //支付成功 ==代打卡
        record.setStatus(WAIT_PAY_SUCCESS.getCode());

        //支付类型 ==微信支付
        record.setPayType(OrderPayTypeEnum.WECHAT_PAY.getCode());
        record.setOpenIdChannel("福礼惠工作号");
        record.setOutTradeNo("weixinout2012111");
        //支付时间
        Date payDate = new Date();
        record.setPayDate(payDate);

        DateUtils.formatWebFormat(payDate);
        //支付时间 +1
        Date date1 = DateUtils.addDays(payDate, 1);
        //打卡结束时间
        record.setPushEndDate(date1);
        //打卡开始时间
        record.setPushStartDate(date1);

        userPunchRecordMapper.insertSelective(record);

    }

}
