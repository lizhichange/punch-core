package com.fulihui.punch.web.controller;

import static org.near.servicesupport.error.Errors.Commons.SYSTEM_ERROR;
import static org.near.webmvcsupport.view.JsonResultBuilder.fail;

import org.near.servicesupport.error.InvokeServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;

/**
 * 统一错误处理
 *
 * @author Willard.Hu on 2016/10/26 0026.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = InvokeServiceException.class)
    String error(InvokeServiceException e) {
        log.error(e.getMessage(), e);
        return JSON.toJSONString(fail(String.valueOf(e.getErrcode()), e.getErrmsg()));
    }

    @ExceptionHandler(value = Exception.class)
    String error(Exception e) {
        log.error(e.getMessage(), e);
        return JSON.toJSONString(
            fail(String.valueOf(SYSTEM_ERROR.getErrcode()), SYSTEM_ERROR.getErrmsg()));
    }

}
