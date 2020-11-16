package com.ys.crm.settings.web.controller;

import com.ys.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = LoginException.class)
    public Map doLoginException(Exception e) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("flag", false);
        map.put("msg", e.getMessage());
        return map;
    }
}
