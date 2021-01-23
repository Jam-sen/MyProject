package com.ys.springcloud.controller;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyErrorController implements ErrorController {
    //当网关工程中的某些过滤器出现异常以后，则会自动执行这个控制器中的这个方法来获取处理异常的请求地址，然后自动执行这个请求
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping("/error")
    @ResponseBody
    public String error() {
        ZuulException zuulException = (ZuulException) RequestContext.getCurrentContext().getThrowable();
        /**
         * 使用全局异常页面，这里可以直接转向到一个视图页面显示错误信息，也可以直接使用Json来显示错误信息。
         *  注意：使用全局异常错误页面必须要启用默认的异常过滤器，因此全局error错误页面与自定义异常的过滤器有冲突二选一即可
         */
        return "全局错误页面Code：" + zuulException.nStatusCode + "---" + "message:" + zuulException.getMessage();
    }
}
