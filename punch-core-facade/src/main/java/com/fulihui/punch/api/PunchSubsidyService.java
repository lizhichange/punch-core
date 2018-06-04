package com.fulihui.punch.api;

import java.util.Date;

import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;

import com.fulihui.punch.dto.PunchSubsidyInfoDTO;
import com.fulihui.punch.request.PunchSubsidyInfoRequest;
import com.fulihui.punch.request.PunchSubsidyInfoUpdateRequest;

/**
 * The interface Punch subsidy service.
 */
public interface PunchSubsidyService {



     /**
      * Query page t page result.
      *
      * @param request the request
      * @return the t page result
      */
     TPageResult<PunchSubsidyInfoDTO> queryPage(PunchSubsidyInfoRequest request);


     /**
      * 更新数据
      *
      * @param request the request
      * @return t single result
      */
     TSingleResult<Boolean> updateStatus(PunchSubsidyInfoUpdateRequest request);


     /**
      * Query by id t single result.
      *
      * @param id the id
      * @return the t single result
      */
     TSingleResult<PunchSubsidyInfoDTO> queryById(Integer id);
     
     
     /**
      * 
      * @param date
      * @return
      */
     TSingleResult<String> queryRedisByDate(Date date);
     
     

}
