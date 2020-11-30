package com.ys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {
    @RequestMapping(value = "/test/some.do",method= RequestMethod.GET)
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        //调用service处理请求，把处理结果放入到ModelAndView并返回
        mv.addObject("result","doSome方法执行");
        mv.setViewName("/index.jsp");
        return mv;
    }
}
