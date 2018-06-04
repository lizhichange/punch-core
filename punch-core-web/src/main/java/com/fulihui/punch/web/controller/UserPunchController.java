/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.web.controller;

import static com.fulihui.punch.web.interceptor.util.SessionContext.getOpenId;
import static com.fulihui.punch.web.interceptor.util.SessionContext.getUserId;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4;
import static org.near.toolkit.common.DateUtils.*;
import static org.near.toolkit.common.EnumUtil.queryByCode;
import static org.near.webmvcsupport.view.JsonResultBuilder.fail;
import static org.near.webmvcsupport.view.JsonResultBuilder.succ;
import static org.near.webmvcsupport.view.PageViewBuilder.build;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.near.servicesupport.result.TPageResult;
import org.near.toolkit.common.DateUtils;
import org.near.toolkit.common.StringUtil;
import org.near.toolkit.model.Money;
import org.near.webmvcsupport.view.JsonResult;
import org.near.webmvcsupport.view.PageForm;
import org.near.webmvcsupport.view.PageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fulihui.punch.api.PunchWechatTokenService;
import com.fulihui.punch.api.UserDetailService;
import com.fulihui.punch.dto.*;
import com.fulihui.punch.enums.OrderPayTypeEnum;
import com.fulihui.punch.enums.OrderStatusEnum;
import com.fulihui.punch.request.UserPunchCountDateRequest;
import com.fulihui.punch.request.UserPunchCreateRequest;
import com.fulihui.punch.request.UserPunchUserIdRequest;
import com.fulihui.punch.request.WechatTokenQueryRequest;
import com.fulihui.punch.web.config.AppConfig;
import com.fulihui.punch.web.config.Props;
import com.fulihui.punch.web.config.WechatConfigFactory;
import com.fulihui.punch.web.controller.form.SignForm;
import com.fulihui.punch.web.controller.vo.*;
import com.fulihui.punch.web.enums.CumulativeStatus;
import com.fulihui.punch.web.integration.*;
import com.fulihui.punch.web.manager.JackpotAmountManager;
import com.fulihui.systemcore.api.WechatTokenService;
import com.fulihui.systemcore.dto.WechatConfig;
import com.fulihui.weixinclient.model.WeixinJsapiSign;
import com.fulihui.weixinclient.model.WeixinJsapiSignBuilder;
import com.fulihui.weixinclient.util.WeixinSign;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author lz
 */
@RestController
@RequestMapping("/api/userPunch")
@Api("用户一元打卡接口")
public class UserPunchController {

    private final transient Logger LOGGER          = LoggerFactory.getLogger(getClass());

    public static final String     GROUP           = "PUNCH_FAIL";

    public static final String     FAIL            = GROUP + ":fail_";

    private final static String    chineseDtFormat = "MM月dd日";

    public final static String     noSecondFormat  = "HH:mm";

    @Autowired
    UserPunchServiceClient         userPunchServiceClient;

    @Autowired
    UserDetailService              userDetailService;

    @Autowired
    UserPunchAmountServiceClient   userPunchAmountServiceClient;

    @Autowired
    AppConfig                      appConfig;

    @Autowired
    Props                          props;

    @Autowired
    UserServiceClient              userServiceClient;

    @Autowired
    UserPunchCountServiceClient    userPunchCountServiceClient;

    @Autowired
    StatisticsServiceClient        statisticsServiceClient;

    @Autowired
    WechatTokenService             wechatTokenService;

    @Autowired
    WechatConfigFactory            wechatConfigFactory;

    @Autowired
    JedisPool                      jedisPool;

    @Autowired
    JackpotAmountManager           jackpotAmountManager;

    /**
     * 支付
     */
    @PostMapping(value = "/payment")
    @ApiOperation(value = "用户微信支付", notes = "用户打卡微信支付", response = JsonResult.class)
    JsonResult<WeixinJSBridgeVO> payment(HttpServletRequest servletRequest,
                                         @RequestBody SignForm form) {
        if (StringUtil.isEmpty(form.getUrl())) {
            return fail("101", "参数为空");
        }
        String userId = getUserId(servletRequest);
        String openId = getOpenId(servletRequest);
        if (userId == null || openId == null) {
            LOGGER.error("用户未登录");
            return fail("401", "用户未登录");
        }
        //每天23:50:00后约定不能支付
        Date now = new Date();
        long timeFormat = Long.parseLong(formatTimeFormat(now));
        long checkTime = Long.parseLong("235000");
        LOGGER.info("当前时间时分秒,timeFormat:{},checkTime", timeFormat, checkTime);
        //如果当前时间大于不能支付
        if (timeFormat >= checkTime) {
            return fail("102", "系统结算中，请稍后再来参加挑战~");
        }
        UserPunchCreateRequest request = new UserPunchCreateRequest();
        request.setOpenId(openId);
        request.setUserId(userId);
        try {
            InetAddress netAddress = InetAddress.getLocalHost();
            if (netAddress != null) {
                request.setHostAddress(netAddress.getHostAddress());
            }
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
        }
        request.setPayAmount(100);
        request.setPayType(OrderPayTypeEnum.WECHAT_PAY.getCode());
        request.setOpenIdChannel("福礼惠公众号");
        request.setBodyString("福礼惠一元打卡");
        try {
            InetAddress netAddress = InetAddress.getLocalHost();
            if (netAddress != null) {
                String host = netAddress.getHostAddress();
                request.setHostAddress(host);
            }
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
        }

        String contextPath = servletRequest.getContextPath();
        String concat = appConfig.getWebappHostURL().concat(contextPath + "/weiXinPayNotify");
        request.setNotifyURL(concat);

        UserPunchRecordDTO dto = userPunchServiceClient.queryLately(userId);
        if (dto != null
            && StringUtil.equals(OrderStatusEnum.WAIT_PAY_SUCCESS.getCode(), dto.getStatus())) {
            LOGGER.error("userId:{},你已成功参加挑战，请勿重复支付.", userId);
            return fail("101", "你已成功参加挑战，请勿重复支付。");
        }
        String order = userPunchServiceClient.createUserPunchOrder(request);
        if (StringUtil.isEmpty(order)) {
            return fail("101", "未生成订单");
        }
        WeixinJSBridgeVO vo = weixinPaySign(order, form.getUrl());
        LOGGER.info("微信支付,vo:{}", vo);
        return succ(vo);
    }

    @PostMapping("/currentState")
    @ApiOperation(value = "查询用户当前状态", notes = "查询用户当前状态")
    JsonResult<UserPunchStatusVO> currentState(HttpServletRequest servletRequest) {
        String userId = getUserId(servletRequest);
        Assert.notNull(userId, "userId is not null");
        UserPunchRecordDTO dto = userPunchServiceClient.queryLately(userId);
        UserPunchStatusVO vo = new UserPunchStatusVO();
        if (dto == null) {
            vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
            return succ(vo);
        }
        String status = dto.getStatus();
        switch (queryByCode(status, OrderStatusEnum.class)) {
            /**
             * 初始状态
             */
            case WAIT_NO_PAY:
                vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
                break;
            /**
             * 支付失败
             */
            case WAIT_PAY_FAIL:
                vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
                break;
            /**
             *支付中
             */
            case WAIT_ING_PAY:
                vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
                break;
            /**
             * 支付成功==待打卡==到计时
             */
            case WAIT_PAY_SUCCESS:
                boolean mockPunch = appConfig.isMockPunch();
                if (mockPunch) {
                    vo.setStatus("CAN_HIT_PAY");
                    break;
                }
                Date now = new Date();
                LOGGER.info("支付成功,待打卡,当前时间是否进入打卡时间内,timeFormat:{}", timeFormat);
                //如果当前时间 和打卡时间是同一天
                long timeFormat = Long.parseLong(formatTimeFormat(now));
                if (Objects.equals(formatWebFormat(now), formatWebFormat(dto.getPeriodDate()))) {
                    if (timeFormat >= appConfig.getPunchStartTime()
                        && timeFormat <= appConfig.getPunchEndTime()) {
                        //可以打卡
                        vo.setStatus("CAN_HIT_PAY");
                    } else {
                        vo.setStatus(OrderStatusEnum.WAIT_PAY_SUCCESS.getCode());
                    }
                } else {
                    vo.setStatus(OrderStatusEnum.WAIT_PAY_SUCCESS.getCode());
                }
                //倒计时
                vo.setSurplusSecond(diffSeconds(new Date(), dto.getPeriodDate()));
                break;
            /**
             * 已打卡  提示用户支付,继续挑战下期
             */
            case WAIT_ALREADY:
                vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
                break;
            /**
             * 未打卡,打卡失败,提示用户支持挑战下期
             */
            case WAIT_NOT_CARE:
                vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
                vo.setFailStatus(OrderStatusEnum.WAIT_NOT_CARE.getCode());
                String webFormat = formatWebFormat(dto.getPeriodDate());
                String newFormat = formatNewFormat(dto.getPayDate());
                String key = FAIL + userId + "_" + webFormat + "_" + newFormat;
                try (Jedis resource = jedisPool.getResource()) {
                    if (StringUtil.isNotBlank(resource.get(key))) {
                        vo.setDisplay(false);
                    } else {
                        vo.setDisplay(true);
                        resource.set(key, "1");
                    }
                }
                break;
            /**
             * 已完成 用户打卡返利完成 提示用户支付,继续挑战下期
             */
            case COMPLETED:
                vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
                break;
            default:
                vo.setStatus(OrderStatusEnum.WAIT_NO_PAY.getCode());
        }

        return succ(vo);
    }

    /**
     * 打卡倒计时计算
     *
     * @return 剩余多少秒
     */
    private long diffSeconds(Date now, Date periodDate) {
        //如果当前时间 和打卡时间是同一天
        if (Objects.equals(formatWebFormat(now), formatWebFormat(periodDate))) {
            String web = formatWebFormat(now) + " 08:00:00";
            try {
                Date newFormat = parseNewFormat(web);
                long diffSeconds = getDiffSeconds(newFormat, now);
                LOGGER.info("倒计时:{}", diffSeconds);
                return diffSeconds;
            } catch (ParseException e) {
                LOGGER.error(e.getMessage(), e);
            }

        }
        String web = formatWebFormat(addDays(now, 1)) + " 08:00:00";
        try {
            Date newFormat = parseNewFormat(web);
            long diffSeconds = getDiffSeconds(newFormat, now);
            LOGGER.info("倒计时:{}", diffSeconds);
            return diffSeconds;
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return 0;
    }

    /**
     * 打卡
     */
    @PostMapping("/punch")
    @ApiOperation(value = "用户打卡", notes = "用户打卡", response = JsonResult.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JsonResult punch(HttpServletRequest servletRequest) {
        String userId = getUserId(servletRequest);
        Date date = new Date();
        long timeFormat = Long.parseLong(formatTimeFormat(date));
        long punchStartTime = appConfig.getPunchStartTime();
        long punchEndTime = appConfig.getPunchEndTime();
        LOGGER.info("timeFormat:{},punchStartTime:{},punchEndTime:{}", timeFormat, punchStartTime,
            punchEndTime);
        //是否mock打卡
        if (appConfig.isMockPunch()) {
            String punch = userPunchServiceClient.punch(userId);
            LOGGER.info("用户打卡结果,punch:{},userId:{}", punch, userId);
            return succ(punch);
        }

        if (timeFormat < punchStartTime) {
            LOGGER.warn("未到打卡时间,userId:{}", userId);
            return fail("101,", "未到打卡时间");
        }

        if (timeFormat > punchEndTime) {
            LOGGER.warn("打卡时间已过,userId:{}", userId);
            return fail("101,", "打卡时间已过");
        }
        String punch = null;
        try {
            punch = userPunchServiceClient.punch(userId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail("101,", "来早了，明天再来打卡吧");
        }
        return succ(punch);
    }

    @PostMapping("/cumulative")
    @ApiOperation(value = "查询打卡统计奖池金额,参与人数", notes = "查询打卡统计奖池金额,参与人数", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JsonResult<CumulativeVO> cumulative(HttpServletRequest servletRequest) {
        String userId = getUserId(servletRequest);
        Assert.notNull(userId, "userId is not null");
        //查询用户信息
        UserPunchRecordDTO dto = userPunchServiceClient.queryLately(userId);
        if (dto == null) {
            //查询最近
            CumulativeVO vo = jackpotAmountManager.queryLatelyJackpotAmount();
            return succ(vo);
        }

        CumulativeVO vo;
        String status = dto.getStatus();
        switch (queryByCode(status, OrderStatusEnum.class)) {
            //支付成功
            case WAIT_PAY_SUCCESS:

                Date now = new Date();
                //如果当前时间 和打卡时间是同一天
                if (Objects.equals(formatWebFormat(now), formatWebFormat(dto.getPeriodDate()))) {
                    vo = jackpotAmountManager.queryPeriodDate(dto.getPeriodDate());
                    vo.setStatus(CumulativeStatus.TODAY.getCode());
                } else {
                    vo = jackpotAmountManager.queryPeriodDate(dto.getPeriodDate());
                    vo.setStatus(CumulativeStatus.TOMORROW.getCode());
                }
                break;
            //支付中
            case WAIT_ING_PAY:
                //查询最近
                vo = jackpotAmountManager.queryLatelyJackpotAmount();
                break;
            //已打卡
            case WAIT_ALREADY:
                vo = jackpotAmountManager.queryLatelyJackpotAmount();
                break;
            //已完成
            case COMPLETED:
                vo = jackpotAmountManager.queryLatelyJackpotAmount();
                break;
            //未支付
            case WAIT_NO_PAY:
                vo = jackpotAmountManager.queryLatelyJackpotAmount();
                break;
            //未打卡
            case WAIT_NOT_CARE:
                vo = jackpotAmountManager.queryLatelyJackpotAmount();
                break;
            default:
                vo = jackpotAmountManager.queryLatelyJackpotAmount();
                break;
        }
        return succ(vo);
    }

    @PostMapping("/myRecord")
    @ApiOperation(value = "我的战绩", notes = "我的战绩查询接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JsonResult<UserMyRecordVO> myRecord(HttpServletRequest servletRequest) {
        String userId = getUserId(servletRequest);
        Assert.notNull(userId, "userId is not null");
        UserDto userDTO = userDetailService.queryByUserId(userId);
        if (userDTO == null) {
            LOGGER.error("用户信息未查询到,userId:{}", userId);
            return fail("101", "用户未查询到");
        }
        UserMyRecordVO vo = new UserMyRecordVO();
        vo.setHeadImg(userDTO.getHeadImg());
        vo.setNickname(userDTO.getNickName());
        UserMyCumulativeVO cumulative = new UserMyCumulativeVO();
        UserPunchCountDTO dto = userPunchCountServiceClient.queryUserId(userId, null, null);
        if (dto == null) {
            cumulative.setSuccessfulPunch(0);
        } else {
            //打卡成功总次数
            cumulative.setSuccessfulPunch(dto.getTotalCount());
        }

        UserPunchAmountDTO amount = userPunchAmountServiceClient.queryUserAmount(userId);
        if (amount == null) {
            cumulative.setCumulativeInput(new Money(0).toString());
            cumulative.setCumulativeEarning(new Money(0).toString());
        } else {
            Money moneyPay = new Money();
            moneyPay.setCent(amount.getPayAmount());
            cumulative.setCumulativeInput(moneyPay.toString());
            Money moneyMake = new Money();
            moneyMake.setCent(amount.getMakeAmount());
            cumulative.setCumulativeEarning(moneyMake.toString());
        }
        vo.setCumulativeVO(cumulative);
        return succ(vo);
    }

    @PostMapping("/punchSituation")
    @ApiOperation(value = "打卡状况", notes = "打卡状况", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JsonResult<SituationVO> punchSituation(HttpServletRequest servletRequest) {
        String userId = getUserId(servletRequest);
        Assert.notNull(userId, "userId is not null");

        Date now = new Date();

        long timeFormat = Long.parseLong(formatTimeFormat(now));
        //如果当前时间小于配置的打卡的结束时间,
        //显示昨天的数据,t-1
        Date periodDate;
        if (timeFormat <= appConfig.getPunchEndTime()) {
            periodDate = addDays(now, -1);
        } else {
            periodDate = parseWebFormat(now);
        }
        UserPunchStatisticsDTO dto = statisticsServiceClient.queryPeriodDate(periodDate);
        if (dto == null) {
            SituationVO vo = getSituationVO(now);
            return succ(vo);
        }
        SituationVO vo = getSituationVO(dto);
        return succ(vo);

    }

    private SituationVO getSituationVO(UserPunchStatisticsDTO dto) {
        SituationVO vo = new SituationVO();
        vo.setFailNum(dto.getFailNum().intValue());
        vo.setSuccessNum(dto.getSuccessNum().intValue());
        vo.setSituationTime(format(dto.getPeriodDate(), chineseDtFormat));
        return vo;
    }

    private SituationVO getSituationVO(Date now) {
        SituationVO vo = new SituationVO();
        vo.setFailNum(0);
        vo.setSuccessNum(0);
        vo.setSituationTime(format(addDays(now, -1), chineseDtFormat));
        return vo;
    }

    Date parseWebFormat(Date date) {

        String webFormat = formatWebFormat(date);
        try {
            return DateUtils.parseWebFormat(webFormat);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return date;

    }

    @PostMapping("/userPunchList")
    @ApiOperation(value = "用户打卡战绩列表", notes = "用户打卡战绩列表接口", httpMethod = "POST")
    @ApiImplicitParam(name = "form", value = "用户打卡战绩列表分页参数信息", required = true, dataType = "PageForm")
    JsonResult<UserPunchListSelfVO> userPunchList(HttpServletRequest servletRequest,
                                                  @RequestBody PageForm form) {
        String userId = getUserId(servletRequest);
        Assert.notNull(userId, "userId is not null");
        LOGGER.info("userPunchList.form:{}", form);
        UserPunchListSelfVO vo = new UserPunchListSelfVO();
        //查询自己的信息
        UserPunchListVO self = null;

        try {
            Date date = new Date();
            String start = DateUtils.formatWebFormat(date) + " 00:00:00";
            String end = DateUtils.formatWebFormat(date) + " 23:59:59";
            UserPunchCountDTO dto = userPunchCountServiceClient.queryUserId(userId,
                DateUtils.parseNewFormat(start), DateUtils.parseNewFormat(end));
            if (dto != null) {
                self = new UserPunchListVO();
                self.setContinuousCount(dto.getContinuousCount());
                UserDto userDTO = userDetailService.queryByUserId(dto.getUserId());
                assert userDTO != null;
                self.setHeadImg(userDTO.getHeadImg());
                self.setNickName(userDTO.getNickName());
                self.setPunchDateStr(format(dto.getSignTime(), noSecondFormat));
                UserPunchAmountDTO amount = userPunchAmountServiceClient.queryUserAmount(userId);
                assert amount != null;
                Integer makeAmount = amount.getMakeAmount();
                Money money = new Money();
                money.setCent(makeAmount);
                self.setMyAmount(money.toString());

            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        UserPunchCountDateRequest request = new UserPunchCountDateRequest();
        request.setRows(form.getRows());
        request.setPage(form.getPage());
        request.setUserId(userId);
        try {
            Date date = new Date();
            String start = DateUtils.formatWebFormat(date) + " 00:00:00";
            String end = DateUtils.formatWebFormat(date) + " 23:59:59";
            request.setEndDate(DateUtils.parseNewFormat(end));
            request.setStartDate(DateUtils.parseNewFormat(start));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        TPageResult<UserPunchCountDTO> result = userPunchCountServiceClient
            .queryPageFilter(request);
        List<UserPunchCountDTO> values = result.getValues();
        List<UserPunchListVO> transform = values.stream().map(input -> {
            UserPunchListVO listVO = new UserPunchListVO();
            assert input != null;
            UserDto userDTO = userDetailService.queryByUserId(input.getUserId());
            assert userDTO != null;
            listVO.setNickName(userDTO.getNickName());
            listVO.setHeadImg(userDTO.getHeadImg());
            listVO.setContinuousCount(input.getContinuousCount());
            listVO.setPunchDateStr(format(input.getSignTime(), noSecondFormat));
            UserPunchAmountDTO amount = userPunchAmountServiceClient
                .queryUserAmount(input.getUserId());
            assert amount != null;
            Integer makeAmount = amount.getMakeAmount();
            Money money = new Money();
            money.setCent(makeAmount);
            listVO.setMyAmount(money.toString());
            return listVO;
        }).collect(Collectors.toList());
        PageView<UserPunchListVO> pageView = build(transform, form.getPage(), form.getRows(),
            result.getTotalRows());
        vo.setList(pageView);
        vo.setVo(self);
        return succ(vo);
    }

    @PostMapping("/myRecordDetail")
    @ApiOperation(value = "我的战绩详细列表", notes = "我的战绩详细列表接口", httpMethod = "POST")
    @ApiImplicitParam(name = "form", value = "我的战绩详细分页参数信息", required = true, dataType = "PageForm")
    JsonResult<PageView<UserMyRecordDetailVO>> myRecordDetail(HttpServletRequest servletRequest,
                                                              @RequestBody PageForm form) {
        LOGGER.info("myRecordDetail.form:{}", form);
        String userId = getUserId(servletRequest);

        UserPunchUserIdRequest request = new UserPunchUserIdRequest();
        request.setUserId(userId);
        request.setPage(form.getPage());
        request.setRows(form.getRows());
        List<String> arrayList = Lists.newArrayList(OrderStatusEnum.WAIT_NOT_CARE.getCode(),
            OrderStatusEnum.WAIT_ALREADY.getCode(), OrderStatusEnum.COMPLETED.getCode(),
            OrderStatusEnum.WAIT_PAY_SUCCESS.getCode());
        request.setStatusList(arrayList);
        TPageResult<UserPunchRecordDTO> result = userPunchServiceClient.queryPage(request);
        List<UserPunchRecordDTO> values = result.getValues();
        if (isEmpty(values)) {
            PageView<UserMyRecordDetailVO> pageView = build(newArrayList(), form.getPage(),
                form.getRows(), 0);
            return succ(pageView);
        }

        List<UserMyRecordDetailVO> transform = values.stream().map(input -> {
            UserMyRecordDetailVO vo = new UserMyRecordDetailVO();
            assert input != null;
            OrderStatusEnum statusEnum = queryByCode(input.getStatus(), OrderStatusEnum.class);
            if (statusEnum == OrderStatusEnum.WAIT_PAY_SUCCESS) {
                vo.setResultDesc("待打卡");
            }
            if (statusEnum == OrderStatusEnum.WAIT_NOT_CARE) {
                vo.setResultDesc("打卡失败");
                Money money = new Money();
                money.setCent(input.getPayAmount());
                vo.setResultAmount("-" + money.toString());
            }
            if (statusEnum == OrderStatusEnum.WAIT_ALREADY) {
                vo.setShow(Boolean.TRUE);
            }
            if (statusEnum == OrderStatusEnum.COMPLETED) {
                vo.setShow(Boolean.FALSE);
                vo.setResultDesc("打卡成功");
                Money money = new Money();
                money.setCent((input.getRebateAmount() != null ? input.getRebateAmount() : 0));
                vo.setResultAmount("+" + money.toString());
            }
            vo.setInvolvementTime(format(input.getPushStartDate(), chineseDtFormat));
            return vo;
        }).collect(Collectors.toList());

        PageView<UserMyRecordDetailVO> pageView = build(transform, form.getPage(), form.getRows(),
            result.getTotalRows());
        return succ(pageView);

    }

    @Autowired
    PunchWechatTokenService punchWechatTokenService;

    private String takeAccessToken(String appid, String appsecret) {
        WechatTokenQueryRequest tokenQueryRequest = new WechatTokenQueryRequest();
        tokenQueryRequest.setAppid(appid);
        tokenQueryRequest.setAppsecret(appsecret);

        return punchWechatTokenService.takeAccessToken(tokenQueryRequest);
    }

    private WeixinJSBridgeVO weixinPaySign(String id, String url) {

        WeixinJSBridgeVO vo = new WeixinJSBridgeVO();
        Map<String, Object> map = new HashMap<>();
        WechatConfig config = wechatConfigFactory.getWechatConfig(props.getConfigCode());
        vo.setAppId(config.getAppID());
        String accessToken = takeAccessToken(config.getAppID(), config.getAppsecret());
        LOGGER.info("accessToken: {}", accessToken);
        WeixinJsapiSign weixinJsapiSign = WeixinJsapiSignBuilder.build(config.getAppID(),
            accessToken, unescapeHtml4(url));

        map.put("appId", config.getAppID());
        map.put("timeStamp", weixinJsapiSign.getTimestamp());
        vo.setTimeStamp(weixinJsapiSign.getTimestamp() + "");
        vo.setPackageString("prepay_id=" + id);
        vo.setSignType("MD5");
        map.put("nonceStr", weixinJsapiSign.getNonceStr());
        map.put("package", "prepay_id=" + id);
        map.put("signType", "MD5");
        vo.setNonceStr(weixinJsapiSign.getNonceStr());
        String sign = WeixinSign.genServiceSign(map, config.getSignKey());
        vo.setPaySign(sign);
        return vo;
    }

}
