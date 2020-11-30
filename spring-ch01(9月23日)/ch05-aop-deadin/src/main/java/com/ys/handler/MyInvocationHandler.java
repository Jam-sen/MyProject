package com.ys.handler;

import com.ys.service.impl.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class MyInvocationHandler implements InvocationHandler {
    private SomeServiceImpl someServiceImpl;

    public MyInvocationHandler(SomeServiceImpl someServiceImpl) {
        this.someServiceImpl = someServiceImpl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;
        if ("doOther".equals (method.getName ())) {

            System.out.println (new Date ());
            object = method.invoke (someServiceImpl, args);
        } else {
            object = method.invoke (someServiceImpl, args);
        }

        return object;
    }
}
