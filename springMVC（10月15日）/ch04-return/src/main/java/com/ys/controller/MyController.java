package com.ys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    //处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
    @RequestMapping(value = "/returnString-View.do", method = RequestMethod.POST)
    public String doReturnView(String name, Integer age) {
        System.out.println("doReturnView,name=" + name + "   age=" + age);
        //show:逻辑视图名称，项目中配置了视图解析器
        //框架对视图执行的是forward转发操作
        return "show";
    }

    //处理器方法返回String的方式，如果需要在View中加入处理器方法中的数据，也可以手动添加数据到request作用域
    @RequestMapping(value = "/returnString-View2.do", method = RequestMethod.POST)
    public String doReturnView2(HttpServletRequest request, String name, Integer age) {
        System.out.println("===doReturnView2===,name=" + name + "   age=" + age);
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        return "show";
    }

    //处理器方法返回String，表示完整视图路径，此时不能配置视图解析器
    @RequestMapping(value = "/returnString-View3.do")
    public String doReturnView3(String name, Integer age) {
        System.out.println("==doReturnView3==,name=" + name + "  age=" + age);
        return "/WEB-INF/view/show.jsp";
    }

    //处理器方法返回void，响应ajax请求
    @RequestMapping(value = "/returnVoid-Ajax.do")
    public void doReturnVoidAjax(HttpServletResponse response, String name, Integer age) throws IOException {
        System.out.println("==doReturnVoidAjax==,name=" + name + "  age=" + age);
        //处理ajax，使用json做数据的格式
        //使用student表示处理结果
        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        //把结果的对象转为json格式数据
        if (student != null) {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(student);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.flush();
            writer.close();
        }
    }

    /**
     * 处理器方法返回一个Student，通过框架转为json，响应ajax请求
     *
     * @ResponseBody注解: 作用：把处理器方法返回对象转为json后，通过HttpServletResponse输出给浏览器。
     * 位置：方法的定义上面。 和其它注解没有顺序的关系。
     * 返回对象框架的处理流程：
     * 1. 框架会把返回Student类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canWrite()方法，检查那个HttpMessageConverter接口的实现类能处理Student类型的数据-->MappingJackson2HttpMessageConverter
     * <p>
     * 2.框架会调用实现类的write（）， MappingJackson2HttpMessageConverter的write()方法
     * 把李四同学的student对象转为json（调用Jackson的ObjectMapper实现转为json）
     * contentType: application/json;charset=utf-8
     * <p>
     * 3.框架会调用@ResponseBody把结果数据（json字符串）输出到浏览器， ajax请求处理完成
     */
    @RequestMapping(value = "/returnStudentJson.do")
    @ResponseBody
    public Student doReturnStudentJson(String name, Integer age) {
        //调用service，获取请求结果数据 ， Student对象表示结果数据
        Student student = new Student();
        student.setAge(21);
        student.setName("李四同学");
        return student;//会被框架转为json
    }


    /**
     * 处理器方法返回List<Student>
     * 返回list，框架的处理流程：
     * 1. 框架会把返回List<Student>类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     * 检查那个HttpMessageConverter接口的实现类能处理Student类型的数据-->MappingJackson2HttpMessageConverter
     *
     * 2.框架会调用实现类的write（）， MappingJackson2HttpMessageConverter的write()方法
     * 把李四同学的student对象转为json， 调用Jackson的ObjectMapper实现转为json array
     * contentType: application/json;charset=utf-8
     *
     * 3.框架会调用@ResponseBody把结果数据（json字符串）输出到浏览器， ajax请求处理完成
     */
    @RequestMapping("/returnStudentListJson.do")
    @ResponseBody
    public List<Student> doReturnStudentListJson(String name, Integer age) {
        //调用service，处理结果为students
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setAge(20);
        student.setName("张三");
        Student student1 = new Student();
        student1.setName("李四");
        student1.setAge(21);
        students.add(student);
        students.add(student1);
        return students;
    }

    /**
     * 处理器方法返回的是String，String表示数据的，不是视图。(处理器方法有@ResponseBady注解，返回的String类型就代表数据。如果没有@ResponseBody注解，则代表视图)
     *
     * Content-Type 默认使用："text/plain;charset=ISO-8859-1"，格式和字符集，导致中文有乱码。
     *      解决方案：给RequestMapping增加一个属性produces，使用这个属性指定新的contentType。
     *
     * 返回String数据，框架的处理流程：
     * 1. 框架会把返回返回的string类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canWrite()方法，检查哪个HttpMessageConverter接口的实现类能处理String类型的数据-->StringHttpMessageConverter
     *
     * 2.框架会调用实现类的write（）， StringHttpMessageConverter的write()方法
     * 把字符按照指定的编码处理（默认是text/plain;charset=ISO-8859-1）
     *
     * 3.框架会调用@ResponseBody把结果数据（json字符串）输出到浏览器， ajax请求处理完成
     *
     */
    @ResponseBody
    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=UTF-8")
    public String doReturnStringData(String name, Integer age) {
        return "hello SpringMVC 返回字符串表示数据";
    }
}
