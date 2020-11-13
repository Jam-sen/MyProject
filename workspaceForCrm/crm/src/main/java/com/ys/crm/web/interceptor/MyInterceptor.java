package com.ys.crm.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("user");
        String path = request.getServletPath();
        if (obj != null) {
            System.out.println("放行");
            return true;
        } else if ("/user/login.do".equals(path)) {
            return true;
        } else {
            System.out.println("拦截");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }
    }
}
