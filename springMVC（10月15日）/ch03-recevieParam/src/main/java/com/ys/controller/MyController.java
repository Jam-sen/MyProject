package com.ys.controller;

import com.ys.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 逐个接受请求参数：
     *      要求：处理器方法的形参名和请求中参数名必须一致。（同名的请求参数赋值给同名的形参）
     *      框架内部接受请求参数过程：
     *          1.使用 request对象接收请求参数
     *              String strName = request.getParameter("name")
     *              String strAge = request.getParameter("age")
     *          2.springmvc框架通过DispatcherServlet调用MyController的doSome（）方法时，按名称对应，把接收的参数赋值给形参。
     *              doSome（strName，Integer.valueof(strAge)）
     *       （框架会提供类型转的功能，能String转为int，long，float，double，等类型）
     */
    @RequestMapping(value = "/some.do",method= RequestMethod.POST)
    public ModelAndView doSome(String name,Integer age){
        //可以在方法中直接使用name，age属性
        //注意：如果属性赋值失败，处理器方法就根本不会执行
        ModelAndView mv = new ModelAndView();
        //调用service处理请求，把处理结果放入到ModelAndView并返回
        mv.addObject("result","name="+name+";age="+age);
        mv.setViewName("result");
        return mv;
    }

    /**
     *请求中参数名和处理器方法的形参名不一样的情况
     * @RequestParam: 逐个接收请求参数时，解决请求中参数名和形参名不一样的问题
     *      属性：1.value 请求中的参数名称
     *           2.required 是一个boolean，默认是true
     *                  true：表示请求中必须包含此参数
     *                 false：表示请求中不强制必须含有此参数
     *      位置：在处理器方法的形参定义的前面
     */
    @RequestMapping(value = "/other.do",method = RequestMethod.POST)
    public ModelAndView doOther(@RequestParam(value = "rname",required = false) String name , @RequestParam(value = "rage",required = false) Integer age){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result","name="+name+";age="+age);
        modelAndView.setViewName("result");
        return modelAndView;
    }

    /**
     * 处理器方法的形参是Java对象，这个对象的属性名和请求中参数名一样的
     * 框架会根据形参创建Java对象，给属性赋值。请求中的参数是name，框架会调用setName（）赋值给Student对象，之后再将对象传给处理器方法。
     */
    @RequestMapping(value = "/first.do",method = RequestMethod.POST)
    public ModelAndView doFirst(Student myStudent){
        System.out.println();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result","name="+myStudent.getName()+";age="+myStudent.getAge());
        modelAndView.setViewName("result");
        return modelAndView;
    }
}
