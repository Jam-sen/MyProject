package com.ys.handler;

import com.ys.common.utils.R;
import com.ys.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //全局异常处理器
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Throwable throwable) {
        return R.error().message(throwable.getMessage());
    }

    //自定义异常类处理器
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public R myError(CustomException customException) {
        log.error(customException.getMsg());
        return R.error().code(customException.getCode()).message(customException.getMsg());
    }

}
