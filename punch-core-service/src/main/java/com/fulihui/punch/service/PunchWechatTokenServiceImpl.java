package com.fulihui.punch.service;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulihui.punch.api.PunchWechatTokenService;
import com.fulihui.punch.core.repository.WechatTokenRepository;
import com.fulihui.punch.dal.dataobj.WechatTokenDO;
import com.fulihui.punch.except.CustomerException;
import com.fulihui.punch.request.WechatTokenQueryRequest;
import com.fulihui.punch.util.CommonErrors;
import com.fulihui.punch.util.Consts;
import com.fulihui.systemcore.enums.WechatTokenTypeEnum;
import com.fulihui.weixinclient.WeixinClient;
import com.fulihui.weixinclient.request.AccessTokenWeixinRequest;
import com.fulihui.weixinclient.request.JsapiTicketWeixinRequest;
import com.fulihui.weixinclient.result.AccessTokenWeixinResult;
import com.fulihui.weixinclient.result.JsapiTicketWeixinResult;

/**
 * @author Willard Hu on 2017/10/31.
 */
@Service("punchWechatTokenService")
public class PunchWechatTokenServiceImpl implements PunchWechatTokenService {
    private static final Logger   LOGGER = LoggerFactory
        .getLogger(PunchWechatTokenServiceImpl.class);

    @Autowired
    private WechatTokenRepository wechatTokenRepository;

    @Autowired
    private WeixinClient          weixinClient;

    @Override
    public String takeAccessToken(WechatTokenQueryRequest request) {
        WechatTokenTypeEnum tokenType = WechatTokenTypeEnum.ACCESS_TOKEN;
        WechatTokenDO tokenDO = wechatTokenRepository.queryByType(request.getAppid(), tokenType);
        boolean isnew = (tokenDO == null);
        long nowSec = System.currentTimeMillis() / 1000;
        // 未存储过数据，或者 token 已超时，从接口获取 token 数据，多算1分钟，提前重新获取token
        long l = nowSec + 60;
        if (isnew || (l) >= tokenDO.getExpiresSec()) {

            if (isnew) {
                tokenDO = new WechatTokenDO();
            }
            tokenDO.setAppid(request.getAppid());
            tokenDO.setTokenType(tokenType.getCode());

            AccessTokenWeixinRequest accessTokenRequest = new AccessTokenWeixinRequest();
            accessTokenRequest.setAppid(request.getAppid());
            accessTokenRequest.setSecret(request.getAppsecret());
            AccessTokenWeixinResult accessTokenRes = weixinClient.invokeService(accessTokenRequest);
            if (!accessTokenRes.isSuccess()) {
                throw new RuntimeException(
                    accessTokenRes.getErrcode() + "," + accessTokenRes.getErrmsg());
            }
            tokenDO.setTokenValue(accessTokenRes.getAccess_token());
            tokenDO.setExpiresSec(nowSec + accessTokenRes.getExpires_in());
            LOGGER.info("token信息已失效,远程获取tokenDO:{}",
                reflectionToString(tokenDO, SHORT_PREFIX_STYLE));
        }
        if (isnew) {
            LOGGER.info("最新的token信息落地数据,tokenDO:{}",
                reflectionToString(tokenDO, SHORT_PREFIX_STYLE));
            wechatTokenRepository.insert(tokenDO, Consts.SYSTEM_NAME);
        } else {
            boolean b = wechatTokenRepository.update(tokenDO, Consts.SYSTEM_NAME);
            if (!b) {
                throw new CustomerException(CommonErrors.SYSTEM_ERROR);
            }
        }
        return tokenDO.getTokenValue();
    }

    @Override
    public String takeJsapiTicket(WechatTokenQueryRequest request) {
        WechatTokenTypeEnum tokenType = WechatTokenTypeEnum.JS_API_TICKET;
        WechatTokenDO tokenDO = wechatTokenRepository.queryByType(request.getAppid(), tokenType);
        boolean isnew = (tokenDO == null);
        long nowSec = System.currentTimeMillis() / 1000;

        // 未存储过数据，或者 token 已超时，从接口获取 token 数据，多算1分钟，提前重新获取token
        if (isnew || (nowSec + 60) >= tokenDO.getExpiresSec()) {
            String accessToken = takeAccessToken(request);
            if (isnew) {
                tokenDO = new WechatTokenDO();
            }
            tokenDO.setAppid(request.getAppid());
            tokenDO.setTokenType(tokenType.getCode());
            JsapiTicketWeixinRequest jsapiTicketRequest = new JsapiTicketWeixinRequest();
            jsapiTicketRequest.setAccess_token(accessToken);
            JsapiTicketWeixinResult jsapiTicketRes = weixinClient.invokeService(jsapiTicketRequest);
            if (!jsapiTicketRes.isSuccess()) {
                throw new RuntimeException(
                    jsapiTicketRes.getErrcode() + "," + jsapiTicketRes.getErrmsg());
            }
            tokenDO.setTokenValue(jsapiTicketRes.getTicket());
            tokenDO.setExpiresSec(nowSec + jsapiTicketRes.getExpires_in());
        }
        if (isnew) {
            wechatTokenRepository.insert(tokenDO, Consts.SYSTEM_NAME);
        } else {
            boolean b = wechatTokenRepository.update(tokenDO, Consts.SYSTEM_NAME);
            if (!b) {
                throw new CustomerException(CommonErrors.SYSTEM_ERROR);
            }
        }
        return tokenDO.getTokenValue();
    }
}
