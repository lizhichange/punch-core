package com.fulihui.punch.core.repository;

import java.util.Date;
import java.util.List;

import com.fulihui.punch.dal.dataobj.UserPunchRecordExample;
import com.fulihui.punch.dto.UserPunchRecordDTO;

/**
 * @author lz
 */
public interface UserPunchRepository {

    String create(UserPunchRecordDTO dto);

    List<UserPunchRecordDTO> queryUserPunch(String userId, Date periodDate, Date payExtDate);

    List<UserPunchRecordDTO> queryUserPunch(String status, Date periodDate);

    List<UserPunchRecordDTO> queryUserPunch(String status, Date periodDate, int page, int rows);

    long count(String status, Date periodDate);

    long count(String status);

    List<UserPunchRecordDTO> queryUserPunch(String userId);

    List<UserPunchRecordDTO> query(UserPunchRecordExample example);

    long count(UserPunchRecordExample example);

    List<UserPunchRecordDTO> queryUserPunchStatus(String userId, String status, Date date);

    int queryCount(String status, Date date);

    List<UserPunchRecordDTO> queryUserPunchStatus(String status, int page, int rows);

    List<UserPunchRecordDTO> queryUserPunchStatus(String status, Date date, int page, int rows);

    UserPunchRecordDTO queryByPunchOrderId(String userId, String punchOrderId);

    boolean updateStatus(UserPunchRecordDTO dto);

    boolean updateStatusByPunchOrderId(String userId, String punchOrderId, String status,
                                       Date punchDate);

    /**
     * 支付后更新
     */
    boolean updateAfterPayed(UserPunchRecordDTO dto);

    UserPunchRecordDTO queryByPrimaryKey(String punchOrderId, String userId);
}
