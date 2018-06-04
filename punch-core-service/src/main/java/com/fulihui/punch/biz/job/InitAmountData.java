package com.fulihui.punch.biz.job;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulihui.punch.core.repository.PunchJackpotAmountRepository;
import com.fulihui.punch.core.repository.PunchSubsidyInfoRepository;
import com.fulihui.punch.core.repository.UserPunchStatisticsRepository;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfo;
import com.fulihui.punch.dto.PunchJackpotAmountDTO;
import com.fulihui.punch.dto.PunchSubsidyInfoDTO;
import com.fulihui.punch.dto.UserPunchStatisticsDTO;
import com.fulihui.punch.enums.SubsidyStatusEnum;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-1-24
 * Time: 14:21
 * @author lizhi
 */
@Component
public class InitAmountData {
    public static final Logger    LOGGER = LoggerFactory.getLogger(InitAmountData.class);
    @Autowired
    UserPunchStatisticsRepository userPunchStatisticsRepository;
    @Autowired
    PunchSubsidyInfoRepository    punchSubsidyInfoRepository;
    @Autowired
    PunchJackpotAmountRepository  punchJackpotAmountRepository;

    private Date getPeriodDate() {
        Date now = new Date();
        try {
            String webFormat = DateUtils.formatWebFormat(now);
            now = DateUtils.parseWebFormat(webFormat);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return now;
    }

    private Date getNextPeriodDate() {
        Date now = new Date();
        try {
            String webFormat = DateUtils.formatWebFormat(DateUtils.addDays(now, 1));
            now = DateUtils.parseWebFormat(webFormat);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return now;
    }

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        LOGGER.info("初始化奖池金额开始");
        //执行初始化每期奖池金额
        Date periodDate = getPeriodDate();
        Date nextPeriodDate = getNextPeriodDate();
        try {
            init(periodDate, nextPeriodDate);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            //执行初始化每期打卡状况统计
            UserPunchStatisticsDTO situationDTO = userPunchStatisticsRepository
                .queryPeriodDate(periodDate);
            if (situationDTO == null) {
                UserPunchStatisticsDTO newDTO = new UserPunchStatisticsDTO();

                newDTO.setPayAmount(0L);
                newDTO.setSuccessNum(0L);
                newDTO.setPeriodDate(periodDate);
                newDTO.setPayOneAmount(0L);
                newDTO.setSubsidyAmount(0L);
                newDTO.setTotalAmount(0L);
                newDTO.setSubsidySetupAmount(0L);
                newDTO.setFailNum(0L);
                newDTO.setPartakeNum(0L);

                userPunchStatisticsRepository.save(newDTO);
            }

            UserPunchStatisticsDTO next = userPunchStatisticsRepository
                .queryPeriodDate(nextPeriodDate);
            if (next == null) {
                UserPunchStatisticsDTO nextDTO = new UserPunchStatisticsDTO();
                nextDTO.setPayAmount(0L);
                nextDTO.setSuccessNum(0L);
                nextDTO.setPeriodDate(nextPeriodDate);
                nextDTO.setPayOneAmount(0L);
                nextDTO.setSubsidyAmount(0L);
                nextDTO.setTotalAmount(0L);
                nextDTO.setSubsidySetupAmount(0L);
                nextDTO.setFailNum(0L);
                nextDTO.setPartakeNum(0L);
                userPunchStatisticsRepository.save(nextDTO);
            }

            LOGGER.info("situationDTO:{}",
                ToStringBuilder.reflectionToString(situationDTO, ToStringStyle.SHORT_PREFIX_STYLE));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            //执行初始化补贴金额金额
            LOGGER.info("nextPeriodDate:{}", nextPeriodDate);
            PunchSubsidyInfo punchSubsidyInfo = punchSubsidyInfoRepository
                .queryAsDate(nextPeriodDate);

            LOGGER.info("punchSubsidyInfo:{}", ToStringBuilder.reflectionToString(punchSubsidyInfo,
                ToStringStyle.SHORT_PREFIX_STYLE));
            if (punchSubsidyInfo == null) {
                PunchSubsidyInfoDTO dto = new PunchSubsidyInfoDTO();
                dto.setAsDate(nextPeriodDate);
                dto.setStatus(SubsidyStatusEnum.INIT.getCode());

                Integer integer = punchSubsidyInfoRepository.create(dto);
                LOGGER.info("pk:{},dto:{}", integer, dto);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("初始化奖池金额结束");
    }

    private void init(Date periodDate, Date nextPeriodDate) {

        PunchJackpotAmountDTO amountDTO = punchJackpotAmountRepository.queryPeriodDate(periodDate);
        if (amountDTO == null) {
            PunchJackpotAmountDTO newDTO = new PunchJackpotAmountDTO();
            newDTO.setCumulativeAmount(0);
            newDTO.setCumulativeNumber(0);
            newDTO.setPeriodDate(periodDate);
            punchJackpotAmountRepository.save(newDTO);
        }
        LOGGER.info("amountDTO:{}", amountDTO);

        PunchJackpotAmountDTO next = punchJackpotAmountRepository.queryPeriodDate(nextPeriodDate);
        if (next == null) {
            PunchJackpotAmountDTO newDTO = new PunchJackpotAmountDTO();
            newDTO.setCumulativeAmount(0);
            newDTO.setCumulativeNumber(0);
            newDTO.setPeriodDate(nextPeriodDate);
            punchJackpotAmountRepository.save(newDTO);
        }
        LOGGER.info("next:{}", next);
    }
}
