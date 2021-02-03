package com.ys.shiro.handler;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ShiroException.class})
    public String permissionError(Throwable e) {
        System.out.println(e.getClass());
        //转向到没有权限的视图页面，可以利用参数Throwable将错误信息写入浏览器，实际工作中应该根据参数的类型来判断具体是什么异常，然后根据不同的异常为用户提示不同的异常信息
        return "noPermission";
    }
}
