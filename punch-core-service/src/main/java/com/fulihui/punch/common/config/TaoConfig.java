package com.fulihui.punch.common.config;

import org.near.toolkit.model.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-3-12
 * Time: 11:29
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TaoConfig extends ToString {

    private static final long serialVersionUID = -5320586390413876878L;
    @Value("${tao.appKey}")
    private String            taoAppKey;

    @Value("${tao.appSecret}")
    private String            taoAppSecret;

    @Value("${tao.router}")
    private String            taoRouter;
    @Value("${tao.adzoneId}")
    private String            taoAdzoneId;



}
