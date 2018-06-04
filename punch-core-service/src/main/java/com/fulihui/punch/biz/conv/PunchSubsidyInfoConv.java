package com.fulihui.punch.biz.conv;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fulihui.punch.dal.dataobj.PunchSubsidyInfo;
import com.fulihui.punch.dto.PunchSubsidyInfoDTO;

/**
 * @author lizhi
 */
public class PunchSubsidyInfoConv {
    


    public static PunchSubsidyInfo toDO(PunchSubsidyInfoDTO dto) {

        if (dto == null) {
            return null;
        }
        PunchSubsidyInfo record = new PunchSubsidyInfo();
        BeanUtils.copyProperties(dto, record);
        return record;
    }

    public static PunchSubsidyInfoDTO toDTO(PunchSubsidyInfo record) {

        if (record == null) {
            return null;
        }
        PunchSubsidyInfoDTO dto = new PunchSubsidyInfoDTO();
        BeanUtils.copyProperties(record, dto);
        return dto;
    }

    public static List<PunchSubsidyInfoDTO> toList(List<PunchSubsidyInfo> list) {

        if (isEmpty(list)) {
            return newArrayList();
        }
        return list.stream().map(PunchSubsidyInfoConv::toDTO).collect(Collectors.toList());

    }



}
