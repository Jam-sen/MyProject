package com.ys.service.impl;

import com.ys.service.SomeService;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println ("执行doSome");
    }

    @Override
    public void doOther() {
        System.out.println ("执行doOther");
    }
}
