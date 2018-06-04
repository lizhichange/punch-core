package com.fulihui.punch.biz.job;

import static com.fulihui.punch.biz.job.TaoDataJob.LOGGER;
import static com.fulihui.punch.enums.PrincipalTypeEnum.USER;

import java.util.Date;
import java.util.List;

import org.near.toolkit.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.punch.biz.processor.WechatManager;
import com.fulihui.punch.core.repository.WechatAuthRepository;
import com.fulihui.punch.core.zbus.Const;
import com.fulihui.punch.dal.dataobj.TaoKeRecord;
import com.fulihui.punch.dal.dataobj.TaoKeRecordExample;
import com.fulihui.punch.dal.mapper.TaoKeRecordMapper;
import com.fulihui.punch.dto.WechatAuthDto;
import com.fulihui.punch.request.WeixinAccountRequest;

/**
 *
 * @author Administrator
 * @date 2018/3/12 0012
 */
@Component
public class TaoKeAmountJob implements SimpleJob {

    private volatile boolean     running = false;

    @Autowired
    private TaoKeRecordMapper    taoKeRecordMapper;

    @Autowired
    private WechatAuthRepository wechatAuthRepository;

    @Autowired
    private WechatManager        wechatManager;

    @Override
    public void execute(ShardingContext arg0) {
        LOGGER.info("running:{}", running);
        if (running) {
            LOGGER.warn("TaoKeAmountJob.running值没改掉");
            return;
        }
        running = true;

        running = false;
    }

    void init() {
        Date date = new Date();
        String startWebFormat = DateUtils.formatWebFormat(date) + " 00:00:00";
        String endWebFormat = DateUtils.formatWebFormat(date) + " 23:59:59";
        LOGGER.info("startWebFormat:{},endWebFormat:{}", startWebFormat, endWebFormat);
        try {

            Date startDate = DateUtils.parseNewFormat(startWebFormat);
            Date endDate = DateUtils.parseNewFormat(endWebFormat);
            TaoKeRecordExample example = new TaoKeRecordExample();
            TaoKeRecordExample.Criteria criteria = example.createCriteria();
            criteria.andGmtCreateGreaterThanOrEqualTo(startDate);
            criteria.andGmtCreateLessThanOrEqualTo(endDate);
            criteria.andStatusEqualTo("3");
            List<TaoKeRecord> taoKeRecords = taoKeRecordMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(taoKeRecords)) {
                for (TaoKeRecord tk : taoKeRecords) {
                    String userId = tk.getUnionId();
                    WechatAuthDto wechatAuthDto = wechatAuthRepository.queryByPrincipalId(userId,
                        USER);
                    if (wechatAuthDto != null) {
                        //                        if (check(userId)) {
                        //                            continue;
                        //                        }

                        WeixinAccountRequest request = new WeixinAccountRequest();
                        request.setAmount(2000L);
                        request.setUserId(userId);
                        request.setOpenId(wechatAuthDto.getOpenId());
                        request.setType(Const.TAOBAOKE);
                        request.setDesc("淘宝拉新奖励");
                        request.setOutOrderId(tk.getId() + "");
                        wechatManager.withdrawalAmountTk(request);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private boolean check(String userId) {
        //按照 总单（≤5笔） 去发
        TaoKeRecordExample recordExample = new TaoKeRecordExample();
        recordExample.createCriteria().andUnionIdEqualTo(userId).andStatusEqualTo("4");
        List<TaoKeRecord> records = taoKeRecordMapper.selectByExample(recordExample);

        if (!CollectionUtils.isEmpty(records) && records.size() >= 5) {
            return true;
        }
        return false;
    }
}
