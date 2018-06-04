package com.fulihui.punch.biz.conv;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fulihui.punch.dal.dataobj.UserPunchAmount;
import com.fulihui.punch.dto.UserPunchAmountDTO;

/**
 * @author lizhi
 */
public class UserPunchAmountConv {

    public static UserPunchAmount toDO(UserPunchAmountDTO dto) {

        if (dto == null) {
            return null;
        }
        UserPunchAmount record = new UserPunchAmount();
        BeanUtils.copyProperties(dto, record);
        return record;
    }

    public static UserPunchAmountDTO toDTO(UserPunchAmount record) {

        if (record == null) {
            return null;
        }
        UserPunchAmountDTO dto = new UserPunchAmountDTO();
        BeanUtils.copyProperties(record, dto);
        return dto;
    }

    public static List<UserPunchAmountDTO> toList(List<UserPunchAmount> list) {

        if (isEmpty(list)) {
            return newArrayList();
        }
        return list.stream().map(UserPunchAmountConv::toDTO).collect(Collectors.toList());

    }

}
