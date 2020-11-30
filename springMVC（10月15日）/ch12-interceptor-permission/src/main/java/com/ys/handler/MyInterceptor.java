package com.ys.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


//拦截器类：拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {


    //验证登录的用户的信息，正确return true，其他return false
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("11111拦截器MyInterceptor的preHandler（）");
        String name = "";
        //从session中取出用户数据
        Object attr = request.getSession().getAttribute("name");
        if (attr != null) {
            name=(String) attr;
        }
        //判断登录的账号是否符合要求
        if (!"zs".equals(name)) {
            //不能访问系统
            //给用户提示
            request.getRequestDispatcher("tips.jsp").forward(request, response);
            return false;
        }
        return true;
    }

}
