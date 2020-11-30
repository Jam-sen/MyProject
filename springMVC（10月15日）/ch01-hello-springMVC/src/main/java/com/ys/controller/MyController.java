package com.ys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Contorller: 表示当前类为处理器，创建处理器对象，并放在springmvc容器之中。
 *      位置：在类的上面
 *      和@Service，@Component一样
 */
@Controller
public class MyController {
    //处理用户提交的请求，Springmvc中是使用方法来处理的
    //方法是自定义的，可以有多种返回值，多种参数，方法名称自定义

    /**
     * 准备使用dosome方法处理some.do请求。
     * @RequestMapping : 请求映射，把指定的请求交给方法来处理，被注解的方法的方法名可以随意。
     *      位置：方法上，也可以在类上；
     */
    @RequestMapping(value = {"/some.do","/first.do"})
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        //调用service处理请求，把处理结果放入到ModelAndView并返回
        mv.addObject("result","doSome方法执行");
        mv.setViewName("result");
        return mv;
    }

    @RequestMapping({"/other.do", "/second.do"})
    public ModelAndView doOther(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result","doOther方法执行了");
        System.out.println(modelAndView.getViewName());
        modelAndView.setViewName("result");
        return modelAndView;
    }
}
