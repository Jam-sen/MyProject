package com.ys;

import com.ys.ba06.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Pointcut注解 {
    @Test
    public void test06(){
        String config = "ApplicationContext06.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        SomeService someService = (SomeService) ac.getBean ("someService");
        someService.doThird ();

    }
}
