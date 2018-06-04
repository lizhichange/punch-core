package com.fulihui.punch.biz.job;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.near.toolkit.common.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.fulihui.punch.biz.model.Result;
import com.fulihui.punch.common.config.TaoConfig;
import com.fulihui.punch.dal.dataobj.TaoKeRecord;
import com.fulihui.punch.dal.dataobj.TaoKeRecordExample;
import com.fulihui.punch.dal.mapper.TaoKeRecordMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgNewuserOrderGetRequest;
import com.taobao.api.response.TbkDgNewuserOrderGetResponse;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-1-24
 * Time: 14:21
 * @author lizhi
 */
@Component
public class TaoDataJob implements SimpleJob {
    public static final Logger LOGGER  = LoggerFactory.getLogger(TaoDataJob.class);

    @Autowired
    TaoKeRecordMapper          taoKeRecordMapper;
    @Autowired
    TaoConfig                  taoConfig;
    private volatile boolean   running = false;

    @Override
    public void execute(ShardingContext shardingContext) {
        LOGGER.info("running:{}", running);
        if (running) {
            LOGGER.warn("TaoDataJob.running值没改掉");
            return;
        }
        running = true;

        boolean hashNext = true;
        Long i = 1L;
        while (hashNext) {
            TaobaoClient client = new DefaultTaobaoClient(taoConfig.getTaoRouter(),
                taoConfig.getTaoAppKey(), taoConfig.getTaoAppSecret());
            TbkDgNewuserOrderGetRequest req = new TbkDgNewuserOrderGetRequest();
            req.setPageSize(100L);
            req.setPageNo(i);
            req.setAdzoneId(Long.parseLong(taoConfig.getTaoAdzoneId()));
            TbkDgNewuserOrderGetResponse rsp = null;
            try {
                rsp = client.execute(req);
            } catch (ApiException e) {
                LOGGER.info(e.getMessage(), e);
            }
            if (rsp != null) {

                Result result = JSON.parseObject(rsp.getBody(), Result.class, new Feature[0]);
                if (result != null) {

                    Result.TbkDgNewuserOrderGetResponseBean tbkDgNewuserOrderGetResponse = result
                        .getTbk_dg_newuser_order_get_response();
                    Result.TbkDgNewuserOrderGetResponseBean.ResultsBeanX results = tbkDgNewuserOrderGetResponse
                        .getResults();

                    Result.TbkDgNewuserOrderGetResponseBean.ResultsBeanX.DataBean.ResultsBean results1 = results
                        .getData().getResults();
                    List<Result.TbkDgNewuserOrderGetResponseBean.ResultsBeanX.DataBean.ResultsBean.MapDataBean> mapData = results1
                        .getMap_data();

                    LOGGER.info("淘宝客数据条数:{}", mapData.size());
                    for (Result.TbkDgNewuserOrderGetResponseBean.ResultsBeanX.DataBean.ResultsBean.MapDataBean bean : mapData) {
                        TaoKeRecordExample example = new TaoKeRecordExample();
                        example.createCriteria()
                            .andTbTradeParentIdEqualTo(bean.getTb_trade_parent_id());
                        List<TaoKeRecord> list = taoKeRecordMapper.selectByExample(example);
                        if (CollectionUtils.isEmpty(list)) {
                            TaoKeRecord record = new TaoKeRecord();
                            record.setBindTime(getDate(bean.getBind_time()));

                            record.setMemberId(bean.getMember_id());
                            record.setOrderTkType(String.valueOf(bean.getOrder_tk_type()));
                            record.setRegisterTime(getDate(bean.getRegister_time()));
                            record.setSiteId(String.valueOf(bean.getSite_id()));
                            record.setSiteName(bean.getSite_name());
                            record.setMobile(bean.getMobile());
                            record.setStatus(String.valueOf(bean.getStatus()));
                            record.setUnionId(bean.getUnion_id());
                            record.setBuyTime(getDate(bean.getBuy_time()));
                            record.setTbTradeParentId(bean.getTb_trade_parent_id());
                            record.setAdzoneId(String.valueOf(bean.getAdzone_id()));
                            record.setAdzoneName(bean.getAdzone_name());
                            record.setMemberNick(bean.getMember_nick());
                            record.setGmtCreate(new Date());
                            record.setGmtModified(new Date());

                            taoKeRecordMapper.insertSelective(record);
                        } else {
                            LOGGER.debug("信息已经同步:{}", list.get(0));
                        }
                    }
                    hashNext = results.getData().isHas_next();
                    i++;

                    //hashNext = false;
                }

            }

        }

        running = false;
    }

    Date getDate(String date) {
        try {
            return DateUtils.parseNewFormat(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }

}
