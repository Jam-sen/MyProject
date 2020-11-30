package com.ys;

import com.ys.ba08.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class 有接口还使用cglib动态代理 {
    @Test
    public void test07(){
        String config = "ApplicationContext08.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        SomeService someService = (SomeService) ac.getBean ("someService");
        System.out.println (someService.getClass ().getName ());
        /**
         * 目标类没有接口，使用cglib动态代理，spring框架会自动应用cglib。（代理类类型是：com.ys.ba07.SomeServiceImpl$$EnhancerBySpringCGLIB$$ea86acab）
         */
        someService.doThird ();
    }
}
