package com.crud.exception;

import com.crud.vo.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public ResponseBean ExceptionHandler(GlobalException e) {
        log.error(e.getMessage(), e);
        return ResponseBean.fail(e.getResponseEnum());
    }

}
