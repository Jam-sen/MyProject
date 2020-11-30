package com.ys.ba07;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Aspect :是aspect框架中的注解
 * 作用:表示当前类是切面类
 * 切面类：是用来给业务方法增加功能的类，在这个类中有切面的功能代码
 * 位置：在类定义的上面
 */
@Aspect
public class MyAspect {
    /**
     * 最终通知方法的定义格式
     *  1.public
     *  2.没有返回值
     *  3.方法名自定义
     *  4.方法只有一个参数（JoinPoint），可选。
     */

    /**
     * @After: 最终通知
     *      属性：value 切入点表达式
     *      位置：方法定义的上面
     *      特点：1.总是会执行
     *            2.在目标方法之后执行的
     */
    @After (value = "myPt()")
    public void myAfter(){
        System.out.println ("执行最终通知，总是会被执行的代码");
        //一般做资源清除工作的
    }
    @Before (value = "myPt()")
    public void myBefore(){
        System.out.println ("前置通知，在目标方法之前先执行");
    }

    /**
     * @Pointcut: 定义和管理切入点，如果你的项目中有多个切入点表达式是重复的，可以复用的。可以使用@Pointcut统一管理
     *      属性：value 切入点表达式
     *      位置：在自定义的方法的上面
     *      特点：当使用@Pointcut定义在一个方法的上面，此时这个方法的名称就是切入点表达式的别名。其他的通知中，value属性就可以使用这个方法的名称，代替切入点表达式了。（这个方法只用来定义公共的切入点表达式，一般定义为private）
     */
    @Pointcut(value = "execution(* *..SomeServiceImpl.doThird(..))")
    private void myPt(){
        //无需代码
    }
}
