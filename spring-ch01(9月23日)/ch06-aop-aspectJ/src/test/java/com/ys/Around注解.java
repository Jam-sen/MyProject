package com.ys;

import com.ys.ba03.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Around注解 {
    @Test
    public void test03(){
        String config = "ApplicationContext03.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        SomeService someService = (SomeService) ac.getBean ("someService");
        String result = someService.doFirst ("张三",28);
        System.out.println (result);
    }
}
