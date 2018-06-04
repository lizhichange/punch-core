package com.fulihui.punch.web.integration.impl;

import java.util.List;
import java.util.Map;

import org.near.servicesupport.util.ServiceResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulihui.punch.web.integration.BannerServiceClient;
import com.fulihui.welfarecentre.facade.api.BannerService;
import com.fulihui.welfarecentre.facade.dto.BannerDTO;
import com.fulihui.welfarecentre.facade.request.BannerMultiLocalRequest;
import com.fulihui.welfarecentre.facade.result.BannerMultiLocalResult;
/**
 * @author lizhi
 */
@Component
public class BannerServiceClientImpl implements BannerServiceClient{
    
    @Autowired
    private BannerService bannerService;
    
    
    @Override
    public Map<String,List<BannerDTO>> queryMultiLocal(BannerMultiLocalRequest request){
        BannerMultiLocalResult result = bannerService.queryMultiLocal(request);
        ServiceResultUtil.checkResult(result);
        return result.getValues();
    }
    

}
