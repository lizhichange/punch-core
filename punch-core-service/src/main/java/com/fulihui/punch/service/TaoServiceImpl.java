package com.fulihui.punch.service;

import static org.near.servicesupport.error.Errors.Commons.REQUEST_PARAMETER_ERROR;
import static org.near.servicesupport.util.ServiceAssert.notBlank;
import static org.near.servicesupport.util.ServiceAssert.notNull;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.near.servicesupport.result.ResultBuilder;
import org.near.servicesupport.result.TMultiResult;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;
import org.near.toolkit.common.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.TaoService;
import com.fulihui.punch.dal.dataobj.TaoKeRecord;
import com.fulihui.punch.dal.dataobj.TaoKeRecordExample;
import com.fulihui.punch.dal.dataobj.TaoPrompt;
import com.fulihui.punch.dal.dataobj.TaoPromptExample;
import com.fulihui.punch.dal.mapper.ExtTaoKeRecordMapper;
import com.fulihui.punch.dal.mapper.TaoKeRecordMapper;
import com.fulihui.punch.dal.mapper.TaoPromptMapper;
import com.fulihui.punch.dto.TaoKeDTO;
import com.fulihui.punch.dto.TaoPromptDTO;
import com.fulihui.punch.request.TaoKeRequest;
import com.fulihui.punch.request.TaoPromptRequest;
import com.fulihui.punch.result.TaoKeStatisticsResult;
import com.google.common.collect.Lists;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-3-12
 * Time: 19:09
 */
@Service("taoService")
public class TaoServiceImpl implements TaoService {

    @Autowired
    TaoKeRecordMapper taoKeRecordMapper;

    @Autowired
    TaoPromptMapper   taoPromptMapper;

    @Override
    public TPageResult<TaoKeDTO> queryPage(TaoKeRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);

        TaoKeRecordExample example = getTaoKeRecordExample(request);
        example.setOffset(request.start4Mysql());
        example.setLimit(request.getRows());

        List<TaoKeRecord> list = taoKeRecordMapper.selectByExample(example);
        if (isEmpty(list)) {
            return ResultBuilder.succTPage(Lists.newArrayList(), request.getPage(),
                request.getRows(), 0);
        }
        List<TaoKeDTO> collect = list.stream().map(item -> {

            TaoKeDTO r = new TaoKeDTO();
            BeanUtils.copyProperties(item, r);
            return r;
        }).collect(Collectors.toList());

        long count = taoKeRecordMapper.countByExample(example);
        return ResultBuilder.succTPage(collect, request.getPage(), request.getRows(), (int) count);
    }

    @Override
    public TSingleResult<TaoKeStatisticsResult> query(TaoKeRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        //完成下单
        TaoKeStatisticsResult result = new TaoKeStatisticsResult();
        TaoKeRecordExample doneExample = new TaoKeRecordExample();
        doneExample.createCriteria().andUnionIdEqualTo(request.getUserId())
            .andStatusIn(Lists.newArrayList("3", "4"));
        long done = taoKeRecordMapper.countByExample(doneExample);
        result.setDoneNum(done);
        //邀请人数
        TaoKeRecordExample succExample = new TaoKeRecordExample();
        succExample.createCriteria().andUnionIdEqualTo(request.getUserId());
        long succ = taoKeRecordMapper.countByExample(succExample);
        result.setSuccNum(succ);

        //完成下单
        TaoKeRecordExample amountExample = new TaoKeRecordExample();
        amountExample.createCriteria().andUnionIdEqualTo(request.getUserId())
            .andStatusIn(Lists.newArrayList("3", "4"));
        long amount = taoKeRecordMapper.countByExample(amountExample);
        result.setAmount(amount);
        return ResultBuilder.succTSingle(result);
    }

    @Autowired
    ExtTaoKeRecordMapper extTaoKeRecordMapper;

    TaoKeRecordExample convert(Date beginTime, Date endTime, String unionId, String status) {

        TaoKeRecordExample example = new TaoKeRecordExample();
        TaoKeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUnionIdEqualTo(unionId);

        if (beginTime != null) {
            criteria.andGmtCreateGreaterThanOrEqualTo(beginTime);
        }
        if (endTime != null) {
            criteria.andGmtCreateLessThanOrEqualTo(endTime);
        }

        criteria.andStatusEqualTo(status);
        return example;
    }

    @Override
    public TPageResult<TaoKeStatisticsResult> group(TaoKeRequest request) {
        List<TaoKeStatisticsResult> list = Lists.newArrayList();
        notNull(request, REQUEST_PARAMETER_ERROR);
        List<TaoKeRecord> group = extTaoKeRecordMapper.group(request.getBeginTime(),
            request.getEndTime(), request.start4Mysql(), request.getRows());
        if (isEmpty(group)) {
            return ResultBuilder.succTPage(Lists.newArrayList(), request.getPage(),
                request.getRows(), 0);
        }
        int count = extTaoKeRecordMapper.count(request.getBeginTime(), request.getEndTime());

        for (TaoKeRecord taoKeRecord : group) {

            TaoKeStatisticsResult result = new TaoKeStatisticsResult();
            String unionId = taoKeRecord.getUnionId();
            TaoKeRecordExample example = convert(request.getBeginTime(), request.getEndTime(),
                unionId, "3");
            Long l = taoKeRecordMapper.countByExample(example);
            result.setBuyNum(l);

            TaoKeRecordExample example1 = convert(request.getBeginTime(), request.getEndTime(),
                unionId, "1");
            long l1 = taoKeRecordMapper.countByExample(example1);
            result.setRegNum(l1);

            TaoKeRecordExample example2 = convert(request.getBeginTime(), request.getEndTime(),
                unionId, "2");
            long l2 = taoKeRecordMapper.countByExample(example2);
            result.setBindNum(l2);

            list.add(result);
        }
        return ResultBuilder.succTPage(list, request.getPage(), request.getRows(), count);
    }

    @Override
    public TMultiResult<TaoPromptDTO> queryPrompt(TaoPromptRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        TaoPromptExample example = new TaoPromptExample();
        TaoPromptExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(request.getCode())) {
            criteria.andCodeEqualTo(request.getCode());
        }
        List<TaoPrompt> taoPrompts = taoPromptMapper.selectByExample(example);
        if (isEmpty(taoPrompts)) {
            return ResultBuilder.succTMulti(Lists.newArrayList());
        }
        List<TaoPromptDTO> collect = taoPrompts.stream().map(item -> {
            TaoPromptDTO r = new TaoPromptDTO();
            BeanUtils.copyProperties(item, r);
            return r;
        }).collect(Collectors.toList());
        return ResultBuilder.succTMulti(collect);
    }

    @Override
    public TSingleResult<TaoPromptDTO> modifyPrompt(TaoPromptRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notNull(request.getCode(), REQUEST_PARAMETER_ERROR);
        notBlank(request.getContent(), REQUEST_PARAMETER_ERROR);
        TaoPromptExample example = new TaoPromptExample();
        example.createCriteria().andCodeEqualTo(request.getCode());
        List<TaoPrompt> taoPrompts = taoPromptMapper.selectByExample(example);
        if (isEmpty(taoPrompts)) {
            return ResultBuilder.succTSingle(null);
        }
        TaoPrompt taoPrompt = new TaoPrompt();
        taoPrompt.setCode(taoPrompts.get(0).getCode());
        taoPrompt.setContent(request.getContent());
        taoPrompt.setGmtModified(new Date());
        taoPromptMapper.updateByPrimaryKeySelective(taoPrompt);
        TaoPromptDTO dto = new TaoPromptDTO();
        BeanUtils.copyProperties(taoPrompt, dto);
        return ResultBuilder.succTSingle(dto);
    }

    private TaoKeRecordExample getTaoKeRecordExample(TaoKeRequest request) {
        TaoKeRecordExample example = new TaoKeRecordExample();
        TaoKeRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(request.getUserId())) {
            criteria.andUnionIdEqualTo(request.getUserId());
        }
        if (request.getBeginTime() != null) {
            criteria.andGmtCreateGreaterThanOrEqualTo(request.getBeginTime());
        }
        if (request.getEndTime() != null) {
            criteria.andGmtCreateLessThanOrEqualTo(request.getEndTime());
        }
        if (StringUtil.isNotBlank(request.getStatus())) {
            criteria.andStatusEqualTo(request.getStatus());
        }

        return example;
    }
}
