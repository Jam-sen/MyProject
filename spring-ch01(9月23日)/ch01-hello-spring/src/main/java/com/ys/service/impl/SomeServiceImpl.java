package com.ys.service.impl;

import com.ys.service.SomeService;

public class SomeServiceImpl implements SomeService {
    public SomeServiceImpl() {
        System.out.println ("SomeServiceImpl的无参构造执行了");
    }

    @Override
    public void doSome() {
        System.out.println ("SomeServiceImpl的doSome方法执行了");

    }
}
