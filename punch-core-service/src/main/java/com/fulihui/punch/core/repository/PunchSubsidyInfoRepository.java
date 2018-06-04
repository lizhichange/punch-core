package com.fulihui.punch.core.repository;

import java.util.Date;
import java.util.List;

import com.fulihui.punch.dal.dataobj.PunchSubsidyInfo;
import com.fulihui.punch.dal.dataobj.PunchSubsidyInfoExample;
import com.fulihui.punch.dto.PunchSubsidyInfoDTO;

public interface PunchSubsidyInfoRepository {
    
    
   Integer create(PunchSubsidyInfoDTO dto);
   
   List<PunchSubsidyInfoDTO> queryPage(PunchSubsidyInfoExample example);
   
   
   boolean updateStatus(PunchSubsidyInfoDTO dto);
   
   
   Integer count(PunchSubsidyInfoExample example);
   
   PunchSubsidyInfo selectByPrimaryKey(Integer id);
   
   
   PunchSubsidyInfo queryAsDate(Date asDate);
   
   
   String queryAmount(Date asDate);

}
