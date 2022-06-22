package com.ys.callable.controller;

import com.ys.callable.domain.ResValue;
import com.ys.callable.service.CallableService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

@Slf4j
@Controller
//@Scope("prototype")
public class CallableController {
    @Autowired
    private CallableService callableService;

    @RequestMapping(value = "/")
    @ResponseBody
    public Callable<ResValue<Object>> getValue() {
        log.info(Thread.currentThread().getName()+"线程开始");

        Callable<ResValue<Object>> result = (() -> {
            return callableService.searchBigData();
        });
        log.info(Thread.currentThread().getName()+"线程结束");
        return result;
    }

    @RequestMapping(value = "/b")
    @ResponseBody
    public ResValue<Object> getValue1() {
        return callableService.searchBigData();
    }

    @RequestMapping(value = "/a")
    @ResponseBody
    public String getValue2() {
        return "成功a";

    }
}
