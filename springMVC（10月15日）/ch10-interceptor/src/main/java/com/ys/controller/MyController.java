package com.ys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {
    @RequestMapping(value = "/some.do",method= RequestMethod.POST)
    public ModelAndView doSome(String name,Integer age) {
        System.out.println("==执行MyController中的doSome方法==");
        //处理some.do请求。相当于vservice调用处理完成了
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("age",age);
        mv.setViewName("result");
        return mv;
    }
}
