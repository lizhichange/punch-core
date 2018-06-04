package com.fulihui.punch.biz.processor.impl;

import java.util.Date;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.near.servicesupport.result.BaseResult;
import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fulihui.punch.biz.message.processor.MessageProcessor;
import com.fulihui.punch.biz.message.processor.MessageProcessorRegister;
import com.fulihui.punch.biz.processor.JackpotAmountManager;
import com.fulihui.punch.common.config.CuratorClientUtils;
import com.fulihui.punch.core.repository.PunchJackpotAmountRepository;
import com.fulihui.punch.core.repository.UserPunchAmountRepository;
import com.fulihui.punch.dto.PunchJackpotAmountDTO;
import com.fulihui.punch.dto.UserPunchAmountDTO;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.MessageTypeEnum;
import com.fulihui.punch.enums.TemplateTypeEnum;
import com.fulihui.punch.request.MessageRequest;

@Component
public class JackpotAmountManagerImpl implements JackpotAmountManager {

    private final static Logger  LOGGER = LoggerFactory.getLogger(JackpotAmountManagerImpl.class);

    @Autowired
    PunchJackpotAmountRepository punchJackpotAmountRepository;
    @Autowired
    MessageProcessorRegister     messageProcessorRegister;

    @Autowired
    UserPunchAmountRepository    userPunchAmountRepository;

    @Autowired
    CuratorClientUtils           curatorClientUtils;

    /**
     *    根节点
     */

    @Override
    public void take(UserPunchRecordDTO dto) {
        Assert.notNull(dto, "JackpotAmountManagerImpl param  userPunchRecordDTO  is not null");

        InterProcessMutex interProcessMutex = new InterProcessMutex(
            curatorClientUtils.getCuratorFramework(), CuratorClientUtils.ROOT_LOCKS);
        try {
            //加锁
            interProcessMutex.acquire();
            LOGGER.info("加锁成功,数据模型dto:{}", dto);
            //关联奖池金额
            takeJackpotUserAmount(dto);
            MessageProcessor processor = messageProcessorRegister
                .getProcessor(MessageTypeEnum.WEIXIN_MESSAGE);
            if (processor != null) {
                MessageRequest messageRequest = new MessageRequest();
                messageRequest.setChannel(TemplateTypeEnum.DAKA_SIGN.getCode());
                messageRequest.setOpenId(dto.getOpenId());
                messageRequest.setFirst(
                    "挑战已激活！\n" + "请于" + DateUtils.format(dto.getPeriodDate(),
                        DateUtils.chineseDtFormat) + "8:00-10:00完成打卡，瓜分挑战金！\n"
                                        + "注意：因央行要求，收取奖励必须完成微信支付实名认证。如长时间未收到奖励，请检查下自己是否认证过哟~\n");
                messageRequest.setKeyword1("福礼惠天天打卡");
                messageRequest
                    .setKeyword2(DateUtils.format(dto.getPeriodDate(), DateUtils.chineseDtFormat));
                messageRequest.setRemark("\n ➜  领取每日超值福利");
                BaseResult result = processor.sendMessage(messageRequest);
                LOGGER.info("支付成功,推送消息模版结果:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);

        } finally {
            //判断是否持有锁 进而进行锁是否释放的操作
            if (interProcessMutex.isAcquiredInThisProcess()) {
                try {
                    interProcessMutex.release();
                    LOGGER.info("释放锁成功,成功数据模型dto:{}", dto);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }

        }
    }

    /**
     * 关联奖池金额并且 用户累计支付信息
     * @param dto   UserPunchRecordDTO
     */
    private void takeJackpotUserAmount(UserPunchRecordDTO dto) {
        //期号时间 关联奖池金额
        Date periodDate = dto.getPeriodDate();
        PunchJackpotAmountDTO amountDTO = punchJackpotAmountRepository.queryPeriodDate(periodDate);
        //如果等于空
        if (amountDTO == null) {
            PunchJackpotAmountDTO newDTO = new PunchJackpotAmountDTO();
            newDTO.setCumulativeAmount(dto.getPayAmount());
            newDTO.setCumulativeNumber(1);
            newDTO.setPeriodDate(periodDate);
            punchJackpotAmountRepository.save(newDTO);
        } else {
            PunchJackpotAmountDTO update = new PunchJackpotAmountDTO();
            update.setId(amountDTO.getId());
            //支付金额 + 累计金额
            update.setCumulativeAmount(amountDTO.getCumulativeAmount() + dto.getPayAmount());
            update.setCumulativeNumber(amountDTO.getCumulativeNumber() + 1);
            int i = punchJackpotAmountRepository.update(update);
            LOGGER.info("统计金额,返回影响数:{}", i);
        }
        //用户累计支付
        UserPunchAmountDTO query = userPunchAmountRepository.query(dto.getUserId(),
            dto.getPeriodDate());
        if (query == null) {
            LOGGER.info("关联用户累计金额,dto:{}", dto);
            UserPunchAmountDTO userAmount = new UserPunchAmountDTO();
            userAmount.setPayAmount(dto.getPayAmount());
            userAmount.setUserId(dto.getUserId());
            userAmount.setPeriodDate(dto.getPeriodDate());
            userPunchAmountRepository.save(userAmount);
        }
    }

}
