package com.ys.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


//拦截器类：拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {
    private long beginTime ;
    /**
     * preHandler叫做预处理方法：使整个项目的入口，门户。当preHandler返回true，请求可以被处理。返回false则方法截止。
     *
     * 参数：
     *    object handler： 被拦截的控制器对象
     * 返回值boolean：
     *   true：表示通过了拦截器的验证，可以执行处理器方法。
     *   false：请求没有通过拦截器的验证，请求到达拦截器就截止了，请求没有被处理
     * 特点：
     *   1。这个方法在控制器方法（MyController的doSome）之前先执行的，用户的请求首先到达此方法
     *   2。在这个方法中可以获取请求的信息，验证请求是否符合要求。可以验证用户是否登陆，验证用户是否有权限访问某个链接地址（url）
     *          如果验证失败，可以截断请求，请求不能被处理
     *          如果验证成功，可以放行请求，此时控制器方法才能执行。
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器MyInterceptor的preHandler（）");
        beginTime=System.currentTimeMillis();
        //给浏览器一个返回结果
//        request.getRequestDispatcher("/tips.jsp").forward(request,response);
        return true;
    }

    /**
     * postHandler：后处理方法
     *  参数：
     *      Object handler：被拦截的控制器对象MyController
     *      ModelAndView modelAndView：处理器方法的返回值
     *  特点：
     *      1。在处理器方法之后执行
     *      2。能够获取到处理器方法的返回值ModelAndView，可以修改ModelAndView中的数据和视图，可以影响到最后的执行结果
     *  作用：
     *      主要是对原来的执行结果做二次修正。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器MyInterceptor的postHandler（）");
        //增加数据
        modelAndView.addObject("myDate", new Date());
        //修改视图
        modelAndView.setViewName("show");
    }

    /**
     * afterCompletion：最后执行的方法
     *  参数：
     *      Object handler：被拦截的控制器对象MyController
     *      Exception ex：程序中发生的异常
     *  特点：
     *      1。在请求处理完成后执行的。框架中规定当视图处理完成后，对视图执行了forward，就认为请求处理完成。
     *  作用：
     *      一般做资源回收工作的，程序处理请求过程中创建了一些对象，在这里可以删除，把占用的内存回收。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器MyInterceptor的afterCompletion（）");

        System.out.println("请求执行时间为："+(System.currentTimeMillis()-beginTime));
    }
}
