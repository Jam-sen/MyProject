package com.ys.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //异常过滤器，当其他过滤器出现异常后，自动执行当前过滤器，但是必须要先禁用默认异常过滤器
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取异常对象
        ZuulException exception = (ZuulException) requestContext.getThrowable();
        //获取响应对象
        HttpServletResponse response = requestContext.getResponse();
        //设置响应状态码；响应内容类型和字符集
        response.setStatus(exception.nStatusCode);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("出现异常：" +exception.nStatusCode+"---"+ exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
        return null;
    }
}
