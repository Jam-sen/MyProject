package com.ys;

import static org.junit.Assert.assertTrue;

import com.ys.handler.MyInvocationHandler;
import com.ys.service.SomeService;
import com.ys.service.impl.SomeServiceImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void test01() {
        SomeServiceImpl someServiceImpl = new SomeServiceImpl ();
        InvocationHandler invocationHandler = new MyInvocationHandler (someServiceImpl);
        SomeService someService = (SomeService) Proxy.newProxyInstance (someServiceImpl.getClass ().getClassLoader (),someServiceImpl.getClass ().getInterfaces (),invocationHandler);
        someService.doOther ();
        someService.doSome ();

    }
}
