package com.fulihui.punch.web.integration;

import java.util.List;
import java.util.Map;

import com.fulihui.welfarecentre.facade.dto.BannerDTO;
import com.fulihui.welfarecentre.facade.request.BannerMultiLocalRequest;

/**
 * @author lizhi
 */
public interface BannerServiceClient {
    
    Map<String,List<BannerDTO>> queryMultiLocal(BannerMultiLocalRequest request);

}
