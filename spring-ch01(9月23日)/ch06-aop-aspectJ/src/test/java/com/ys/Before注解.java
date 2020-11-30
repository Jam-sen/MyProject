package com.ys;

import static org.junit.Assert.assertTrue;

import com.ys.ba01.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class Before注解 {
    @Test
    public void test01() {
        String config = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        //从容器中获取目标对象
        SomeService proxy = (SomeService) ac.getBean ("someService");
        System.out.println (proxy.getClass ().getName ());
        proxy.doSome ("zs",20);
    }
}
