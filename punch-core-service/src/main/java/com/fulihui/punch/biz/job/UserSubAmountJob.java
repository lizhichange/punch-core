package com.fulihui.punch.biz.job;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

import org.near.toolkit.common.DateUtils;
import org.near.toolkit.model.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.punch.core.repository.PunchSubsidyInfoRepository;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.core.repository.UserPunchStatisticsRepository;
import com.fulihui.punch.core.zbus.Const;
import com.fulihui.punch.core.zbus.ZbusProducerHandle;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfo;
import com.fulihui.punch.dal.dataobj.UserPunchCount;
import com.fulihui.punch.dal.dataobj.UserPunchCountExample;
import com.fulihui.punch.dal.dataobj.UserPunchStatistics;
import com.fulihui.punch.dal.mapper.ExtUserPunchRecordMapper;
import com.fulihui.punch.dal.mapper.UserPunchCountMapper;
import com.fulihui.punch.dto.PunchSubsidyInfoDTO;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;
import com.fulihui.punch.enums.OrderStatusEnum;
import com.fulihui.punch.enums.SubsidyStatusEnum;
import com.fulihui.punch.enums.TemplateTypeEnum;
import com.fulihui.punch.request.MessageRequest;

/**
 * @author lz
 */
@Component
public class UserSubAmountJob implements SimpleJob {

    private final static Logger        LOGGER  = LoggerFactory.getLogger(UserSubAmountJob.class);

    @Autowired
    ExtUserPunchRecordMapper           extUserPunchRecordMapper;

    @Autowired
    UserPunchRepository                userPunchRepository;

    ExecutorService                    executorService;

    @Autowired
    ZbusProducerHandle                 zbusProducerHandle;

    @Autowired
    UserPunchStatisticsRepository      userPunchStatisticsRepository;

    @Autowired
    UserPunchCountMapper               userPunchCountMapper;

    @Autowired
    private PunchSubsidyInfoRepository punchSubsidyInfoRepository;

    private volatile boolean           running = false;

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            // FIXME: 2018-1-23
            Date now = new Date();
            String webFormat = DateUtils.formatWebFormat(now);
            Date periodDate = DateUtils.parseWebFormat(webFormat);
            LOGGER.info("当前时间:{},每天期号时间:{}", DateUtils.formatNewFormat(now), periodDate);
            statistics(periodDate);

        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void statistics(Date periodDate) {

        LOGGER.info("running:{}", running);
        if (running) {
            LOGGER.warn("UserNotifyMessageJob.running值没改掉");
            return;
        }
        running = true;
        UserPunchStatisticsDTO dto = userPunchStatisticsRepository.queryPeriodDate(periodDate);
        if (dto == null) {
            running = false;
            return;
        }

        UserPunchStatistics updateDTO = new UserPunchStatistics();
        updateDTO.setId(dto.getId());
        Long payAmount = dto.getPayAmount();

        //奖池补贴金额
        PunchSubsidyInfo info = punchSubsidyInfoRepository.queryAsDate(periodDate);
        if (info != null && info.getStatus().equals(SubsidyStatusEnum.INIT.getCode())) {
            PunchSubsidyInfoDTO subsidyDto = new PunchSubsidyInfoDTO();
            subsidyDto.setId(info.getId());
            subsidyDto.setStatus(SubsidyStatusEnum.END.getCode());
            punchSubsidyInfoRepository.updateStatus(subsidyDto);
            //如果补贴金额大于
            if (info.getSubsidyAmount() != null && info.getSubsidyAmount() > 0) {
                Money money = new Money();
                money.setCent(info.getSubsidyAmount());
                //补贴金额
                long setUpAmount = money.getCent();
                updateDTO.setSubsidySetupAmount(setUpAmount);
                LOGGER.info("补贴金额:{},统计参与人数金额:{}", setUpAmount, payAmount);
                payAmount += setUpAmount;
            }
        }
        LOGGER.info("计算打卡活动总金额,补贴金额+参与人数金额:{},单位分", payAmount);
        if (payAmount > 0) {
            //总金额
            Money total = new Money();
            total.setCent(payAmount);
            //打卡成功人数
            Money successNumTotal = new Money();
            //打卡成功人数乘以 100 单位分
            successNumTotal.setCent(dto.getSuccessNum() * 100);
            //总金额 除以打卡成功人数 计算用户每人可赚取金额 包含补贴金额 保留2为小数
            BigDecimal oneAmount = total.getAmount().divide(successNumTotal.getAmount(), 2,
                BigDecimal.ROUND_DOWN);
            LOGGER.info("用户可赚取金额:{}", oneAmount.doubleValue());
            //如果计算出来的用户赚取金额和用户支出一样,平台补贴一分钱
            //判断用单位元来判断check

            Money payOneAmount = new Money();
            if (oneAmount.doubleValue() == 1) {
                LOGGER.info("计算出来用户支出和赚取是一样,平台补贴一分钱,赚取金额:{}", oneAmount.doubleValue());
                //补贴金额(不包含设置的补贴金额)
                Money subsidyMoney = new Money();
                //补贴金额就是人数 因为单位是分.  1 *dto.getSuccessNum()
                subsidyMoney.setCent(dto.getSuccessNum());
                //总补贴金额
                updateDTO.setSubsidyAmount(subsidyMoney.getCent());
                Money oneMoney = new Money();
                oneMoney.setAmount(oneAmount);
                //每人补贴一分钱
                payOneAmount.setCent(1 + oneMoney.getCent());
                LOGGER.info("平台补贴1分加用户支付,赚取金额:{},补贴:{}", payOneAmount, subsidyMoney);
            } else {
                updateDTO.setSubsidyAmount(0L);
                payOneAmount.setAmount(oneAmount);
            }
            updateDTO.setPayOneAmount(payOneAmount.getCent());
            //本次支出总金额
            updateDTO.setTotalAmount(payOneAmount.getCent() * dto.getSuccessNum());
            LOGGER.info("job更新计算用户发放金额数据:{}", reflectionToString(updateDTO, SHORT_PREFIX_STYLE));
            int update = userPunchStatisticsRepository.update(updateDTO);
            if (update > 0) {
                LOGGER.info("job计算用户发放金额成功");
            }
            //计算成功发送模版消息
            send(payOneAmount.getCent(), periodDate);
        }
        running = false;
    }

    public static void main(String[] args) {

    }

    void send(long payOneAmount, Date periodDate) {
        try {
            List<UserPunchRecordDTO> dtoList = userPunchRepository
                .queryUserPunch(OrderStatusEnum.WAIT_ALREADY.getCode(), periodDate);
            if (!CollectionUtils.isEmpty(dtoList)) {
                dtoList.forEach(user -> {

                    UserPunchCountExample example = new UserPunchCountExample();
                    example.createCriteria().andUserIdEqualTo(user.getUserId());

                    List<UserPunchCount> userPunchCounts = userPunchCountMapper
                        .selectByExample(example);

                    Integer count = 0;
                    if (!CollectionUtils.isEmpty(userPunchCounts)
                        && Objects.nonNull(userPunchCounts.get(0).getContinuousCount())) {
                        count = userPunchCounts.get(0).getContinuousCount();
                    }
                    MessageRequest request = new MessageRequest();
                    request.setChannel(TemplateTypeEnum.USER_NOTICE.getCode());
                    request.setOpenId(user.getOpenId());
                    request.setFirst("恭喜你成功瓜分奖金！\n" + "奖励将在24小时内自动发放到您的微信钱包中，请您及时关注。\n" + "您已连续打卡"
                                     + count + "天，坚持打卡才是胜利！\n");
                    request.setKeyword1("福礼惠天天打卡");
                    Money money = new Money();
                    money.setCent(payOneAmount);
                    request.setKeyword2(money.getAmount().toString());
                    String date = DateUtils.format(periodDate, DateUtils.webFormat) + " 10:30";
                    request.setKeyword3(date);
                    request.setRemark("\n ➜  开启新的挑战");
                    boolean result = zbusProducerHandle.commitToMQ(JSON.toJSONString(request),
                        Const.USER_TEMPLATE);
                    if (!result) {
                        LOGGER.error("sen template error data={}", user);
                    }
                });
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
