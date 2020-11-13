package com.ys.crm.settings.web.controller;

import com.ys.crm.exception.LoginException;
import com.ys.crm.settings.domain.User;
import com.ys.crm.settings.service.UserService;
import com.ys.crm.util.JacksonUtil;
import com.ys.crm.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public void login(String loginName,String loginPwd,HttpServletRequest request,HttpServletResponse response) throws LoginException, IOException {
        //将密码的明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();
        User user = userService.login(loginName, loginPwd, ip);
        request.getSession().setAttribute("user",user);
        response.getWriter().write(JacksonUtil.flagJson(true));
    }
}
