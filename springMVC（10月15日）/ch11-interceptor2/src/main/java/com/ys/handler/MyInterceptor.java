package com.ys.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器类：拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {
    private long beginTime;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("11111拦截器MyInterceptor的preHandler（）");
        beginTime = System.currentTimeMillis();

        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("11111拦截器MyInterceptor的postHandler（）");
        //增加数据
        modelAndView.addObject("myDate", new Date());
        //修改视图
        modelAndView.setViewName("show");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("11111拦截器MyInterceptor的afterCompletion（）");

        System.out.println("11111的afterCompletion方法报告请求执行时间为：" + (System.currentTimeMillis() - beginTime));
    }
}
