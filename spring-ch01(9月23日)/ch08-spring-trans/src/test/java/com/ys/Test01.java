package com.ys;

import com.ys.service.Service;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    @Test
    public void test01(){
        String config = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        Service myService = (Service) ac.getBean ("serviceImpl");
        String result = myService.buy (1001,1);
    }
}
