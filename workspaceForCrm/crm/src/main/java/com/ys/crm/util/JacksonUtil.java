package com.ys.crm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JacksonUtil {
    public static String flagJson(boolean flag) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", flag);
        ObjectMapper om = new ObjectMapper();
        String json = "";
        try {
            json = om.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String ObjectJson(Object obj) {
        ObjectMapper om = new ObjectMapper();
        String json = "";
        try {
             json = om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
