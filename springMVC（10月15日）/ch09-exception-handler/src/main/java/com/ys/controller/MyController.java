package com.ys.controller;

import com.ys.exception.AgeException;
import com.ys.exception.MyUserException;
import com.ys.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {
    @RequestMapping(value = "/some.do",method= RequestMethod.POST)
    public ModelAndView doSome(String name,Integer age) throws MyUserException {
        //处理some.do请求。相当于vservice调用处理完成了
        ModelAndView mv = new ModelAndView();
        //根据请求参数抛出异常
        if ("张三".equals(name)) {
            throw new NameException("张三禁止访问");
        }
        if (age == null || age > 60) {
            throw new AgeException("年龄太大！！");
        }

        mv.addObject("name",name);
        mv.addObject("age",age);
        mv.setViewName("result");
        return mv;
    }
}
