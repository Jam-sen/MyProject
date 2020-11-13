package com.ys.crm.web.interceptor;

import com.ys.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

public class LoginFilter implements javax.servlet.Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        User user = (User)request.getSession().getAttribute("user");
        if (user != null) {
            System.out.println("通过");
            chain.doFilter(req,resp);
        } else if ("/login.jsp".equals(path)) {
            chain.doFilter(req,resp);
        } else {
            System.out.println("过滤");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }


}
