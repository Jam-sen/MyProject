package com.ys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {
    /**
     * 处理器方法返回ModelAndView，实现转发forward
     * 语法：setViewName（"forward：视图文件的完整路径"）
     * 特点：不和视图解析器一同使用，就当项目中没有视图解析器
     * 作用：可以转发至视图解析器限制以外的页面
     */
    @RequestMapping(value = "/doForward.do",method= RequestMethod.POST)
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        //调用service处理请求，把处理结果放入到ModelAndView并返回
        mv.addObject("result","doSome方法执行");
        //显示转发
        mv.setViewName("forward:/hello.jsp");
        return mv;
    }

    /**
     * 处理器方法返回ModelAndView，实现重定向redirect
     * 语法：setViewName（"redirect：视图完整路径"）
     * redirect特点：不和视图解析器一同使用，就当项目中没有视图解析器
     *
     * 框架对重定向的操作：
     *  1。框架会吧Model中的简单类型数据，转为String使用，作为hello.jsp的get请求参数使用。
     *          目的是在doRedirect.do和hello.jsp两次请求之间传递数据
     *
     *  2。在目标hello.jsp页面可以通过使用参数集合对象${param}，来获取请求参数值
     *          使用方式：${param.myname}
     *
     *  3。注意：使用重定向方式无法直接访问WEB-INF下的受保护的资源
     */
    @RequestMapping(value = "/doRedirect.do",method= RequestMethod.POST)
    public ModelAndView doRedirect(){
        ModelAndView mv = new ModelAndView();
        //调用service处理请求，把处理结果放入到ModelAndView并返回
        mv.addObject("result","doSome");
        //重定向
        //mv.setViewName("redirect:/hello.jsp");
        //URL变为：http://localhost:8080/ch08_forard_redirect/hello.jsp?result=doSome
        //框架重定向后会将处理结果作为重定向页面的请求参数加入其中
        mv.setViewName("redirect:hello.jsp");

        return mv;
    }

}
