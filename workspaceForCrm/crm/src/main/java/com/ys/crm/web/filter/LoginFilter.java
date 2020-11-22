package com.ys.crm.web.filter;

import com.ys.crm.settings.domain.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements javax.servlet.Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        User user = (User)request.getSession().getAttribute("user");
        if (user != null) {

            chain.doFilter(req,resp);
        } else if ("/login.jsp".equals(path)) {
            chain.doFilter(req,resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }


}
