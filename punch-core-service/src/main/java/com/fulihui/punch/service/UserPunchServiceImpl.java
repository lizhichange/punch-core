package com.fulihui.punch.service;

import static com.fulihui.punch.enums.OrderStatusEnum.*;
import static org.near.servicesupport.error.Errors.Commons.REQUEST_PARAMETER_ERROR;
import static org.near.servicesupport.error.Errors.Commons.SYSTEM_ERROR;
import static org.near.servicesupport.result.ResultBuilder.*;
import static org.near.servicesupport.util.ServiceAssert.notBlank;
import static org.near.servicesupport.util.ServiceAssert.notNull;
import static org.near.toolkit.common.DateUtils.*;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;
import org.near.toolkit.common.DateUtils;
import org.near.toolkit.common.EnumUtil;
import org.near.toolkit.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.fulihui.punch.api.UserPunchService;
import com.fulihui.punch.biz.UserPunchCountManager;
import com.fulihui.punch.biz.message.processor.MessageProcessorRegister;
import com.fulihui.punch.biz.processor.JackpotAmountManager;
import com.fulihui.punch.biz.processor.PaymentProcessor;
import com.fulihui.punch.biz.processor.PaymentProcessorRegister;
import com.fulihui.punch.biz.processor.WechatManager;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.core.zbus.Const;
import com.fulihui.punch.core.zbus.ZbusProducerHandle;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample;
import com.fulihui.punch.dal.mapper.UserPunchCountMapper;
import com.fulihui.punch.dto.UserPunchRecordDTO;
import com.fulihui.punch.enums.OrderPayTypeEnum;
import com.fulihui.punch.except.CommonsBizError;
import com.fulihui.punch.model.ZbusPunchModel;
import com.fulihui.punch.request.*;

@Service("userPunchService")
public class UserPunchServiceImpl implements UserPunchService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserPunchServiceImpl.class);

    @Autowired
    PaymentProcessorRegister    paymentProcessorRegister;

    @Autowired
    UserPunchRepository         userPunchRepository;

    @Autowired
    MessageProcessorRegister    messageProcessorRegister;

    @Autowired
    JackpotAmountManager        jackpotAmountManager;
    @Autowired
    UserPunchCountManager       userPunchCountManager;

    @Autowired
    UserPunchCountMapper        userPunchCountMapper;

    @Autowired
    ZbusProducerHandle          zbusProducerHandle;

    @Autowired
    private WechatManager       wechatManager;

    @Override
    public TSingleResult<String> createUserPunchOrder(UserPunchCreateRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        OrderPayTypeEnum typeEnum = EnumUtil.queryByCode(request.getPayType(),
            OrderPayTypeEnum.class);
        notNull(typeEnum, REQUEST_PARAMETER_ERROR);

        PaymentProcessor processor = paymentProcessorRegister.getProcessor(typeEnum);

        if (processor == null) {
            LOGGER.error("订单支付处理器未查询到");
            return failTSingle(SYSTEM_ERROR);
        }

        UserPunchRecordDTO dto = new UserPunchRecordDTO();

        dto.setUserId(request.getUserId());

        dto.setPayType(request.getPayType());

        dto.setOpenId(request.getOpenId());

        dto.setOpenIdChannel(request.getOpenIdChannel());

        dto.setPayDate(request.getPayDate());

        dto.setStatus(WAIT_ING_PAY.getCode());

        dto.setPayAmount(request.getPayAmount());

        String result = processor.payment(dto, request);
        LOGGER.info("支付结果：{}", result);
        if (StringUtil.isNotBlank(result)) {
            return succTSingle(result);
        }

        return failTSingle(SYSTEM_ERROR);

    }

    @Override
    public TSingleResult<UserPunchRecordDTO> queryWaitPay(UserPunchUserIdRequest request) {
        //todo 时间
        notNull(request, REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        List<UserPunchRecordDTO> list = userPunchRepository
            .queryUserPunchStatus(request.getUserId(), WAIT_PAY_SUCCESS.getCode(), null);
        return !isEmpty(list) ? succTSingle(list.get(0)) : succTSingle(null);
    }

    @Override
    public TSingleResult<UserPunchRecordDTO> queryLately(UserPunchUserIdRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        List<UserPunchRecordDTO> list = userPunchRepository.queryUserPunch(request.getUserId());
        return !isEmpty(list) ? succTSingle(list.get(0)) : succTSingle(null);
    }

    @Override
    public TSingleResult<UserPunchRecordDTO> queryPeriodDate(UserPunchPeriodDateRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notNull(request.getPeriodDate(), REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        List<UserPunchRecordDTO> list = userPunchRepository.queryUserPunch(request.getUserId(),
            request.getPeriodDate(), null);
        return !isEmpty(list) ? succTSingle(list.get(0)) : succTSingle(null);
    }

    @Override
    public TSingleResult<UserPunchRecordDTO> queryPayExtDate(UserPunchPayExtDateRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notNull(request.getPayExtDate(), REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        List<UserPunchRecordDTO> list = userPunchRepository.queryUserPunch(request.getUserId(),
            null, request.getPayExtDate());
        return !isEmpty(list) ? succTSingle(list.get(0)) : succTSingle(null);
    }

    @Override
    public TSingleResult<String> payNotifySuccess(WeChatPayNotifyRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        notBlank(request.getPunchOrderId(), REQUEST_PARAMETER_ERROR);

        UserPunchRecordDTO dto = userPunchRepository.queryByPunchOrderId(request.getUserId(),
            request.getPunchOrderId());
        if (dto == null) {
            LOGGER.error("用户订单号未查询到");
            return failTSingle(SYSTEM_ERROR);
        }
        //该订单号只有支付中才修改为支付成功 ==待打卡
        if (StringUtil.equals(dto.getStatus(), WAIT_ING_PAY.getCode())) {
            dto.setStatus(WAIT_PAY_SUCCESS.getCode());
            Date now = new Date();
            //支付时间
            dto.setPayDate(now);

            String webFormat = formatWebFormat(now);
            try {
                Date formatStartDate = parseNewFormat(webFormat + " 08:00:00");
                Date formatEndDate = parseNewFormat(webFormat + " 10:00:00");
                Date pushStartDate = addDays(formatStartDate, 1);
                Date pushEndDate = addDays(formatEndDate, 1);
                dto.setPushEndDate(pushEndDate);
                dto.setPushStartDate(pushStartDate);
                dto.setPayExtDate(now);
                //打卡期号时间
                dto.setPeriodDate(addDays(parseWebFormat(webFormat), 1));
            } catch (ParseException e) {
                LOGGER.error(e.getMessage(), e);
            }
            boolean b = userPunchRepository.updateAfterPayed(dto);
            if (!b) {
                LOGGER.info("支付回调修改失败,打卡订单信息,dto:{}", dto);
                return failTSingle(SYSTEM_ERROR);
            }
            //支付金额
            LOGGER.info("支付回调修改状态成功,orderId:{},userId:{},outTradeNo:{}", dto.getPunchOrderId(),
                dto.getUserId(), dto.getOutTradeNo());
            //关联奖池金额
            jackpotAmountManager.take(dto);

        }
        return succTSingle(dto.getPunchOrderId());
    }

    @Override
    public TSingleResult<String> payNotifyFail(WeChatPayNotifyRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        notBlank(request.getPunchOrderId(), REQUEST_PARAMETER_ERROR);
        UserPunchRecordDTO dto = userPunchRepository.queryByPunchOrderId(request.getUserId(),
            request.getPunchOrderId());
        if (dto == null) {
            LOGGER.error("用户订单号未查询到");
            return failTSingle(SYSTEM_ERROR);
        }
        //该订单号只有支付中才修改为支付失败 ==待打卡
        if (StringUtil.equals(dto.getStatus(), WAIT_ING_PAY.getCode())) {
            dto.setStatus(WAIT_PAY_FAIL.getCode());
            Date date = new Date();
            //支付时间
            dto.setPayDate(date);
            dto.setPayDecs(request.getPayFailDesc());
            boolean b = userPunchRepository.updateAfterPayed(dto);
            if (!b) {
                return failTSingle(SYSTEM_ERROR);
            }
            LOGGER.info("支付回调修改支付失败状态成功,orderId:{},userId:{},outTradeNo:{}", dto.getPunchOrderId(),
                dto.getUserId(), dto.getOutTradeNo());
        }
        return succTSingle(dto.getPunchOrderId());
    }

    @Override
    public TSingleResult<UserPunchRecordDTO> queryUserPunch(UserPunchUserIdRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        String userId = request.getUserId();
        notBlank(userId, REQUEST_PARAMETER_ERROR);
        UserPunchRecordExample example = new UserPunchRecordExample();

        List<UserPunchRecordDTO> query = userPunchRepository.query(example);
        return null;
    }

    @Override
    @Transactional
    public TSingleResult<String> punch(UserPunchUserIdRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        String userId = request.getUserId();
        notBlank(userId, REQUEST_PARAMETER_ERROR);
        //todo 时间区?
        List<UserPunchRecordDTO> list = userPunchRepository.queryUserPunchStatus(userId,
            WAIT_PAY_SUCCESS.getCode(), null);
        if (isEmpty(list)) {
            LOGGER.error("未查询到用户支付信息,不能执行打卡操作,userId:{}", userId);
            return failTSingle(SYSTEM_ERROR);
        }

        UserPunchRecordDTO dto = list.get(0);
        LOGGER.info("push.UserPunchRecordDTO:{}", dto);
        String punchOrderId = dto.getPunchOrderId();
        Date date = new Date();
        //当前时间
        try {
            String nowWebFormat = DateUtils.formatWebFormat(date);
            String format = DateUtils.formatWebFormat(dto.getPayExtDate());
            LOGGER.info("打卡操作,当前时间:{},支付时间:{}", nowWebFormat, format);
            if (StringUtil.equals(nowWebFormat, format)) {
                LOGGER.info("打卡时间不能和支付时间是同一天,userId:{}", userId);
                // FIXME: 2018-3-6  后续 上层业务操作check
                return failTSingle(CommonsBizError.SYSTEM_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        //打卡状态
        boolean b = userPunchRepository.updateStatusByPunchOrderId(userId, punchOrderId,
            WAIT_ALREADY.getCode(), date);
        if (b) {
            LOGGER.info("用户打卡操作成,userId:{},当前时间now:{}", userId, formatWebFormat(date));
            // FIXME: 2018-1-5 计算连续打卡统计以后改成异步
            boolean sign = userPunchCountManager.calculateSign(request.getUserId(), date);
            if (sign) {
                try {
                    ZbusPunchModel model = new ZbusPunchModel();
                    model.setUserId(dto.getUserId());
                    model.setPunchOrderId(dto.getPunchOrderId());
                    zbusProducerHandle.commitToMQ(JSONObject.toJSONString(model), Const.QUEUE_NAME);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                return succTSingle(punchOrderId);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        LOGGER.error("用户打卡失败,userId:{},当前时间now:{}", userId, formatWebFormat(date));
        return failTSingle(REQUEST_PARAMETER_ERROR);

    }

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        String webFormat = DateUtils.formatWebFormat(date);
        Date format1 = DateUtils.parseWebFormat("2018-03-06");
        String format = DateUtils.formatWebFormat(format1);
        LOGGER.info("打卡操作,当前时间:{},支付时间:{}", webFormat, format);
        if (StringUtil.equals(webFormat, format)) {
            System.out.println(1);
        }
    }

    @Override
    public TSingleResult<Boolean> update(UserPunchUpdateRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        String userId = request.getUserId();
        notBlank(userId, REQUEST_PARAMETER_ERROR);
        UserPunchRecordDTO dto = new UserPunchRecordDTO();
        BeanUtils.copyProperties(request, dto);
        boolean b = userPunchRepository.updateStatus(dto);
        return b ? succTSingle(true) : failTSingle(REQUEST_PARAMETER_ERROR);

    }

    @Override
    public TPageResult<UserPunchRecordDTO> queryPage(UserPunchUserIdRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        UserPunchRecordExample example = toExample(request);
        List<UserPunchRecordDTO> query = userPunchRepository.query(example);
        long count = userPunchRepository.count(getCountExample(request));
        return succTPage(query, request.getPage(), request.getRows(), (int) count);
    }

    private UserPunchRecordExample toExample(UserPunchUserIdRequest request) {
        UserPunchRecordExample example = getCountExample(request);
        int offset = request.getPage() > 1 ? (request.getPage() - 1) * request.getRows() : 0;
        example.setOffset(offset);
        example.setLimit(request.getRows());
        example.setOrderByClause(" PERIOD_DATE DESC, GMT_MODIFIED DESC");
        return example;
    }

    private UserPunchRecordExample getCountExample(UserPunchUserIdRequest request) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        UserPunchRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(request.getUserId())) {
            criteria.andUserIdEqualTo(request.getUserId());
        }
        if (!isEmpty(request.getStatusList())) {
            criteria.andStatusIn(request.getStatusList());
        }
        return example;
    }

    @Override
    public TSingleResult<Boolean> weixinTransferAccount(WeixinAccountRequest request) {
        notNull(request, REQUEST_PARAMETER_ERROR);
        notBlank(request.getUserId(), REQUEST_PARAMETER_ERROR);
        notBlank(request.getOpenId(), REQUEST_PARAMETER_ERROR);
        wechatManager.withdrawalAmount(request);
        return succTSingle(true);
    }

}
