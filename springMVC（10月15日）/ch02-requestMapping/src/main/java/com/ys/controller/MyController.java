package com.ys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Contorller: 表示当前类为处理器，创建处理器对象，并放在springmvc容器之中。
 *      位置：在类的上面
 *      和@Service，@Component一样
 */

/**
 * @RequestMapping :
 *      当@RequestMapping注解放在类上时的作用：给所有请求地址加上公共的上级路径（也就是模块名称）
 *      value: 所有请求地址的公共部分，叫做模块名称
 *      位置：放在类的上面
 */
@Controller
@RequestMapping("/test")
public class MyController {
    /**
     * 此时访问soSome这个控制器方法，需要访问路径为：
     *     http://localhost:8080/ch01_hello_springMVC/test/some.do
     */
    /**
     * @RequestMapping : 请求映射
*             属性： method， 表示请求的方式（表示以何种请求方式才可以访问当前处理器方法）。
*             它的值是RequestMethod类枚举值。
*               例如：表示get请求方式：RequestMethod.GET
*                    表示post请求方式：RequestMethod.POST
     *
     *  如果不用get方式访问当前处理器方法，错误是：
     *  HTTP Status 405 - Request method 'GET' not supported
     */
    //指定some.do使用get请求方式
    @RequestMapping(value = "/some.do",method= RequestMethod.GET)
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        //调用service处理请求，把处理结果放入到ModelAndView并返回
        mv.addObject("result","doSome方法执行");
        mv.setViewName("result");
        return mv;
    }
    //指定other.do使用post请求方式
    @RequestMapping(value = "/other.do",method = RequestMethod.POST)
    public ModelAndView doOther(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result","doOther方法执行了");
        System.out.println(modelAndView.getViewName());
        modelAndView.setViewName("result");
        return modelAndView;
    }
}
