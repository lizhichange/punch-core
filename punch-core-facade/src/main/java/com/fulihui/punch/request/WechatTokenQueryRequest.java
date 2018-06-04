package com.fulihui.punch.request;

import org.near.toolkit.model.ToString;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Willard Hu on 2017/10/31.
 */
@Getter
@Setter
public class WechatTokenQueryRequest extends ToString {
    private static final long serialVersionUID = -8956653808092559899L;
    /**
     * appid
     */
    private String            appid;
    /**
     *  appsecret
     */
    private String            appsecret;
}
