package com.ys.controller;

import com.ys.domain.Student;
import com.ys.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding ("UTF-8");
        String id = request.getParameter ("id");
        String name = request.getParameter ("name");
        String email = request.getParameter ("email");
        String age = request.getParameter ("age");

        /*ApplicationContext ac = null;
        Object attr = getServletContext ().getAttribute (WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        ac = (ApplicationContext)attr;*/

        //使用spring提供的工具类
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext (getServletContext ());
        System.out.println ("spring容器对象信息："+ac.getClass ().getName ());
        StudentService service = (StudentService) ac.getBean ("myService");
        int result = service.addStudent (new Student (Integer.valueOf (id),name,email,Integer.valueOf (age)));
        request.setAttribute ("result",result);
        request.getRequestDispatcher ("/result.jsp").forward (request,response);
    }
}
