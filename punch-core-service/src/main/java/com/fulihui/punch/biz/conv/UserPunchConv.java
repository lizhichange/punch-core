package com.fulihui.punch.biz.conv;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import com.fulihui.punch.dal.dataobj.UserPunchRecord;
import com.fulihui.punch.dto.UserPunchRecordDTO;

/**
 * @author lz
 */
public class UserPunchConv {

    public static UserPunchRecord toDO(UserPunchRecordDTO dto) {

        if (dto == null) {
            return null;
        }
        UserPunchRecord record = new UserPunchRecord();

        record.setPunchOrderId(dto.getPunchOrderId());
        record.setUserId(dto.getUserId());
        record.setOutTradeNo(dto.getOutTradeNo());
        record.setPunchDate(dto.getPunchDate());
        record.setStatus(dto.getStatus());
        record.setPayDate(dto.getPayDate());
        record.setPayType(dto.getPayType());
        record.setOpenId(dto.getOpenId());
        record.setOpenIdChannel(dto.getOpenIdChannel());
        record.setPayDecs(dto.getPayDecs());
        record.setPayAmount(dto.getPayAmount());
        record.setGmtCreate(dto.getGmtCreate());
        record.setGmtModified(dto.getGmtModified());
        record.setPushStartDate(dto.getPushStartDate());
        record.setPushEndDate(dto.getPushEndDate());
        record.setPeriodDate(dto.getPeriodDate());
        record.setPayExtDate(dto.getPayExtDate());
        record.setRebateAmount(dto.getRebateAmount());
        return record;
    }

    public static UserPunchRecordDTO toDTO(UserPunchRecord record) {

        if (record == null) {
            return null;
        }
        UserPunchRecordDTO dto = new UserPunchRecordDTO();

        dto.setPunchOrderId(record.getPunchOrderId());
        dto.setUserId(record.getUserId());
        dto.setOutTradeNo(record.getOutTradeNo());
        dto.setPunchDate(record.getPunchDate());
        dto.setStatus(record.getStatus());
        dto.setPayDate(record.getPayDate());
        dto.setPayType(record.getPayType());
        dto.setOpenId(record.getOpenId());
        dto.setOpenIdChannel(record.getOpenIdChannel());
        dto.setPayDecs(record.getPayDecs());
        dto.setPayAmount(record.getPayAmount());
        dto.setGmtCreate(record.getGmtCreate());
        dto.setGmtModified(record.getGmtModified());
        dto.setPushStartDate(record.getPushStartDate());
        dto.setPushEndDate(record.getPushEndDate());
        dto.setPeriodDate(record.getPeriodDate());
        dto.setPayExtDate(record.getPayExtDate());
        dto.setRebateAmount(record.getRebateAmount());
        return dto;
    }

    public static List<UserPunchRecordDTO> toList(List<UserPunchRecord> list) {

        if (isEmpty(list)) {
            return newArrayList();
        }
        return list.stream().map(UserPunchConv::toDTO).collect(Collectors.toList());

    }

}
