package com.fulihui.punch.biz;

import java.util.Date;

/**
 * The interface User punch count manager.
 * @author lizhi
 */
public interface UserPunchCountManager {
    /**
     * 计算用户连续签到信息
     * @param userId the user id 
     * @param punchDate the punch date 
     * @return the boolean
     */
    boolean calculateSign(String userId, Date punchDate);
}
