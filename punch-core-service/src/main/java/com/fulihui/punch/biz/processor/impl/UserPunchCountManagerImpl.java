package com.fulihui.punch.biz.processor.impl;

import java.text.ParseException;
import java.util.Date;

import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fulihui.punch.biz.UserPunchCountManager;
import com.fulihui.punch.core.repository.UserPunchCountRepository;
import com.fulihui.punch.dto.UserPunchCountDTO;

/**
 * @author lz
 */
@Component
public class UserPunchCountManagerImpl implements UserPunchCountManager {

    private static final Logger  LOGGER        = LoggerFactory
        .getLogger(UserPunchCountManagerImpl.class);

    @Autowired
    UserPunchCountRepository     userPunchCountRepository;

    private static final Integer MAX_THRESHOLD = 1000000;

    @Override
    public boolean calculateSign(String userId, Date punchDate) {
        Assert.notNull(userId, "userId is not null");
        /**
         * punchDate 时间格式  yyyy-MM-dd HH:mm:ss
         */
        LOGGER.info("签到时间：{}", DateUtils.formatNewFormat(punchDate));

        /**
         * last 时间格式  yyyy-MM-dd
         */
        Date last = parseWebFormat(punchDate);


        UserPunchCountDTO dto = userPunchCountRepository.query(userId);
        boolean bl;
        //数据存在
        if (null != dto) {
            //update
            UserPunchCountDTO update = new UserPunchCountDTO();
            update.setId(dto.getId());
            //总次数
            update.setTotalCount(dto.getTotalCount() + 1);
            update.setLastTime(last);
            update.setSignTime(punchDate);
            //已经连续签到次数>= 阈值
            if (dto.getContinuousCount() >= MAX_THRESHOLD) {
                //连续签到次数清零
                update.setContinuousCount(0);
            } else {
                /* 判断是否连续 */
                boolean b = last.getTime() == DateUtils.addDays(dto.getLastTime(), 1).getTime();
                //连续
                if (b) {
                    //连续签到次数加1
                    Integer continuousCount = dto.getContinuousCount() + 1;
                    update.setContinuousCount(continuousCount);
                } else {
                    //连续签到次数清零
                    update.setContinuousCount(1);
                }
            }
            bl = userPunchCountRepository.update(update) > 0;
        } else {
            UserPunchCountDTO newDTO = new UserPunchCountDTO();
            newDTO.setUserId(userId);
            newDTO.setLastTime(last);
            newDTO.setSignTime(punchDate);
            newDTO.setContinuousCount(1);
            newDTO.setTotalCount(1);
            bl = userPunchCountRepository.save(newDTO) > 0;
        }
        return bl;
    }

    /**
     * 
     * @param date
     * @return            date
     */
    private Date parseWebFormat(Date date) {
        String webFormat = DateUtils.formatWebFormat(date);
        try {
            return DateUtils.parseWebFormat(webFormat);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return date;

    }

}
