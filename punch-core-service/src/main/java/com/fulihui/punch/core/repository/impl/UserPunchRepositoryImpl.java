package com.fulihui.punch.core.repository.impl;

import static com.fulihui.punch.biz.conv.UserPunchConv.toDO;
import static com.fulihui.punch.biz.conv.UserPunchConv.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Date;
import java.util.List;

import org.near.dal.sequence.SeqKit;
import org.near.toolkit.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fulihui.punch.biz.conv.UserPunchConv;
import com.fulihui.punch.core.repository.UserPunchRepository;
import com.fulihui.punch.dal.dataobj.UserPunchRecord;
import com.fulihui.punch.dal.dataobj.UserPunchRecordExample;
import com.fulihui.punch.dal.mapper.ExtUserPunchRecordMapper;
import com.fulihui.punch.dal.mapper.UserPunchRecordMapper;
import com.fulihui.punch.dto.UserPunchRecordDTO;

/**
 * @author lizhi
 */
@Repository
public class UserPunchRepositoryImpl implements UserPunchRepository {

    @Autowired
    UserPunchRecordMapper    userPunchRecordMapper;

    @Autowired
    ExtUserPunchRecordMapper extUserPunchRecordMapper;

    @Override
    public String create(UserPunchRecordDTO dto) {
        String punchOrderId = String.valueOf(SeqKit.genId());
        dto.setPunchOrderId(punchOrderId);
        UserPunchRecord record = toDO(dto);
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        int i = userPunchRecordMapper.insertSelective(record);
        if (i > 0) {
            return punchOrderId;
        }
        return null;
    }

    @Override
    public List<UserPunchRecordDTO> queryUserPunch(String userId, Date periodDate,
                                                   Date payExtDate) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        UserPunchRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        if (null != periodDate) {
            criteria.andPeriodDateEqualTo(periodDate);
        }
        if (null != payExtDate) {
            criteria.andPayExtDateEqualTo(payExtDate);
        }
        example.setOrderByClause("gmt_modified desc");
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return toList(list);
    }

    @Override
    public List<UserPunchRecordDTO> queryUserPunch(String status, Date periodDate) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        example.createCriteria().andStatusEqualTo(status).andPeriodDateEqualTo(periodDate);
        example.setOrderByClause("period_date DESC , pay_date DESC");
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return toList(list);
    }

    @Override
    public List<UserPunchRecordDTO> queryUserPunch(String status, Date periodDate, int page,
                                                   int rows) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        example.createCriteria().andStatusEqualTo(status).andPeriodDateEqualTo(periodDate);
        example.setOrderByClause(" user_id DESC ");
        int offset = page > 1 ? (page - 1) * rows : 0;
        example.setOffset(offset);
        example.setLimit(rows);
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return toList(list);
    }

    @Override
    public long count(String status, Date periodDate) {

        UserPunchRecordExample example = new UserPunchRecordExample();
        example.createCriteria().andStatusEqualTo(status).andPeriodDateEqualTo(periodDate);
        return (int) userPunchRecordMapper.countByExample(example);
    }

    @Override
    public long count(String status) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        example.createCriteria().andStatusEqualTo(status);
        return userPunchRecordMapper.countByExample(example);
    }

    @Override
    public List<UserPunchRecordDTO> queryUserPunchStatus(String status, int page, int rows) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        example.createCriteria().andStatusEqualTo(status);
        example.setOrderByClause("period_date DESC , pay_date DESC");
        int offset = page > 1 ? (page - 1) * rows : 0;
        example.setOffset(offset);
        example.setLimit(rows);
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return toList(list);
    }

    @Override
    public List<UserPunchRecordDTO> queryUserPunchStatus(String status, Date date, int page,
                                                         int rows) {

        UserPunchRecordExample example = new UserPunchRecordExample();
        int offset = page > 1 ? (page - 1) * rows : 0;
        example.setOffset(offset);
        example.setLimit(rows);
        example.setOrderByClause(" user_id DESC");
        UserPunchRecordExample.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotBlank(status)) {
            criteria.andStatusEqualTo(status);
        }
        if (date != null) {
            criteria.andPeriodDateEqualTo(date);
        }
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return toList(list);
    }

    @Override
    public UserPunchRecordDTO queryByPrimaryKey(String punchOrderId, String userId) {
        UserPunchRecord userPunchRecord = extUserPunchRecordMapper.selectByPrimaryKey(punchOrderId,
            userId);
        return UserPunchConv.toDTO(userPunchRecord);
    }

    @Override
    public List<UserPunchRecordDTO> queryUserPunch(String userId) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        UserPunchRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("period_date DESC , pay_date DESC");
        example.setLimit(1);
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return toList(list);
    }

    @Override
    public List<UserPunchRecordDTO> query(UserPunchRecordExample example) {
        return toList(userPunchRecordMapper.selectByExample(example));
    }

    @Override
    public long count(UserPunchRecordExample example) {
        return userPunchRecordMapper.countByExample(example);
    }

    @Override
    public List<UserPunchRecordDTO> queryUserPunchStatus(String userId, String status, Date date) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status);
        example.setOrderByClause("period_date DESC , pay_date DESC");
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return toList(list);
    }

    @Override
    public int queryCount(String status, Date date) {
        UserPunchRecordExample example = new UserPunchRecordExample();

        UserPunchRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(status)) {
            criteria.andStatusEqualTo(status);
        }
        if (date != null) {
            criteria.andPeriodDateEqualTo(date);
        }
        return (int) userPunchRecordMapper.countByExample(example);

    }

    @Override
    public UserPunchRecordDTO queryByPunchOrderId(String userId, String punchOrderId) {
        UserPunchRecordExample example = new UserPunchRecordExample();
        example.createCriteria().andUserIdEqualTo(userId).andPunchOrderIdEqualTo(punchOrderId);
        List<UserPunchRecord> list = userPunchRecordMapper.selectByExample(example);
        return isEmpty(list) ? null : toList(list).get(0);
    }

    @Override
    public boolean updateStatus(UserPunchRecordDTO dto) {
        UserPunchRecord record = toDO(dto);
        return extUserPunchRecordMapper.updateStatus(record);

    }

    @Override
    public boolean updateStatusByPunchOrderId(String userId, String punchOrderId, String status,
                                              Date punchDate) {
        return extUserPunchRecordMapper.updateStatusByPunchOrderId(userId, punchOrderId, status,
            punchDate);
    }

    @Override
    public boolean updateAfterPayed(UserPunchRecordDTO dto) {
        UserPunchRecord record = toDO(dto);
        return extUserPunchRecordMapper.updateAfterPayed(record);
    }

}
