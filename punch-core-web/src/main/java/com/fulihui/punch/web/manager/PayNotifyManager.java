package com.fulihui.punch.web.manager;

/**
 * The interface Pay notify manager.
 *
 * @author lz
 */
public interface PayNotifyManager {

    /**
     * 支付回调通知
     *
     * @param sb the sb
     */
    void weiXinPayNotify(StringBuilder sb);

}
