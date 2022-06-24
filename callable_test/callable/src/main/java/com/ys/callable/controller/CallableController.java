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

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

//import StringUtils;

@Slf4j
@Controller
//@Scope("prototype")
public class CallableController {
    @Autowired
    private CallableService callableService;

    @RequestMapping(value = "/xlbb_search")
    @ResponseBody
    public Callable<ResValue<ArrayList<HashMap<String, Object>>>> getValue(String phone, String address, String startDate, String endDate) {

        Callable<ResValue<ArrayList<HashMap<String, Object>>>> result = (() -> {
            return callableService.searchBigData(phone, address, startDate, endDate);
        });
        return result;
    }



//    @RequestMapping(value = "/b")
//    @ResponseBody
//    public ResValue<Object> getValue1() {
//        return callableService.searchBigData();
//    }

    @RequestMapping(value = "/a")
    @ResponseBody
    public String getValue2() {
        return "成功a";

    }

}
