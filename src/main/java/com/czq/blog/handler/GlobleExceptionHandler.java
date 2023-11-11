package com.czq.blog.handler;

import com.czq.blog.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobleExceptionHandler {
    @ExceptionHandler
    public Result exceptionHandler(Exception ex){
        log.error("异常信息："+ex);
        return Result.fail(-999,ex.getMessage());
    }
}
