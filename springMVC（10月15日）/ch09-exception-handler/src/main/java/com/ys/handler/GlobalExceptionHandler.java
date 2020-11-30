package com.ys.handler;

import com.ys.exception.AgeException;
import com.ys.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllerAdvice: 控制器增强（也就是给控制器类增加功能-->异常处理功能）
 *      位置：在类的上面
 *      作用：通过AOP技术给处理器方法增加切面功能（这里是异常处理功能）
 *      注意：必须让框架知道这个注解所在的包名，需要在Springmvc配置文件声明组建扫描器，指定@ControllerAdvice所在的包名
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //定义方法，处理发生的异常
    /**
     * 处理异常的方法和处理器方法的定义一样，可以有多个参数，可以有ModelAndView、String、void，对象类型的返回值
     *      形参：Exception，表示处理器方法中抛出的异常对象。通过形参可以获取发生的异常信息。

     * @ExceptionHandler(异常的class) ：表示异常的类型，当发生此类异常时，由当前方法处理
     */
    @ExceptionHandler(value = NameException.class)
    public ModelAndView doNameException(Exception exception) {
        //处理NameException的异常
        /*异常发生处理逻辑：
            1。需要把异常记录下来，记录到数据库，日志文件
                记录异常发生的事件，哪个方法发生的，异常错误内容。
            2。发送溶脂，把异常的信息通过邮件，短信，微信等方式发送给开发人员
            3。给用户友好的提示
         */
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "张三同学禁止访问");
        mv.addObject("ex", exception);
        mv.setViewName("nameError");
        return mv;
    }
    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception exception) {
        //处理NameException的异常
        /*异常发生处理逻辑：
            1。需要把异常记录下来，记录到数据库，日志文件
                记录异常发生的事件，哪个方法发生的，异常错误内容。
            2。发送溶脂，把异常的信息通过邮件，短信，微信等方式发送给开发人员
            3。给用户友好的提示
         */
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "您的年龄不能大于60");
        mv.addObject("ex", exception);
        mv.setViewName("ageError");
        return mv;
    }

    //处理其他异常，NameException、AgeException以外的，不知类型的异常
    @ExceptionHandler
    public ModelAndView doOtherException(Exception exception) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "其他异常");
        mv.addObject("ex", exception);
        mv.setViewName("defaultError");
        return mv;
    }
}
