package com.fulihui.punch.api;

import org.near.servicesupport.result.TMultiResult;
import org.near.servicesupport.result.TPageResult;
import org.near.servicesupport.result.TSingleResult;

import com.fulihui.punch.dto.TaoKeDTO;
import com.fulihui.punch.dto.TaoPromptDTO;
import com.fulihui.punch.request.TaoKeRequest;
import com.fulihui.punch.request.TaoPromptRequest;
import com.fulihui.punch.result.TaoKeStatisticsResult;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-3-12
 * Time: 19:08
 * @author Administrator
 */
public interface TaoService {

    TPageResult<TaoKeDTO> queryPage(TaoKeRequest request);

    TSingleResult<TaoKeStatisticsResult> query(TaoKeRequest request);

    TPageResult<TaoKeStatisticsResult> group(TaoKeRequest request);

    TMultiResult<TaoPromptDTO> queryPrompt(TaoPromptRequest request);

    TSingleResult<TaoPromptDTO> modifyPrompt(TaoPromptRequest request);

}
