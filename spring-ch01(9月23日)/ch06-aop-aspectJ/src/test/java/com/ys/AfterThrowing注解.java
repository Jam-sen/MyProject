package com.ys;

import com.ys.ba04.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AfterThrowing注解 {
    @Test
    public void test04(){
        String config = "ApplicationContext04.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        SomeService someService = (SomeService) ac.getBean ("someService");
        String result = someService.doSecond ("王五",32);
        System.out.println (result);
    }
}
