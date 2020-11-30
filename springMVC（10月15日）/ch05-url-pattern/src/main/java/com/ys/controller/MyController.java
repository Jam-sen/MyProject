package com.ys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Contorller: 表示当前类为处理器，创建处理器对象，并放在springmvc容器之中。
 * 位置：在类的上面
 * 和@Service，@Component一样
 */

/**
 * @RequestMapping :
 * 当@RequestMapping注解放在类上时的作用：给所有请求地址加上公共的上级路径（也就是模块名称）
 * value: 所有请求地址的公共部分，叫做模块名称
 * 位置：放在类的上面
 */
@Controller
@RequestMapping("/test")
public class MyController {
    @RequestMapping(value = "/some.do",produces = "text/plain;charset=UTF-8")
    public ModelAndView doSome(String name, Integer age) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("show");
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        return modelAndView;
    }
}
