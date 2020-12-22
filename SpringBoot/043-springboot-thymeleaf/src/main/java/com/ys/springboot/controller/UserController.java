package com.ys.springboot.controller;

import com.ys.springboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @RequestMapping(value = "/each/list")
    public String eachList(Model model) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(100 + i);
            user.setAddress("兰州市城关区" + i);
            user.setNick("张" + i);
            user.setPhone("1398888888" + i);
            userList.add(user);
        }
        model.addAttribute("userList", userList);
        return "eachList";
    }

    @RequestMapping(value = "/each/map")
    public String eachMap(Model model) {
        Map<Integer, User> userMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(100 + i);
            user.setAddress("兰州市城关区" + i);
            user.setNick("张" + i);
            user.setPhone("1398888888" + i);
            userMap.put(i, user);
        }
        model.addAttribute("userMap", userMap);
        return "eachMap";
    }

    @RequestMapping(value = "/each/array")
    public ModelAndView eachArray() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("eachArray");
        User[] users = new User[10];
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setPhone("1398888888" + i);
            user.setId(100 + i);
            user.setAddress("兰州市城关区" + i);
            user.setNick("王" + i);
            users[i] = user;
        }
        modelAndView.addObject("userArray", users);
        return modelAndView;
    }

    @RequestMapping(value = "/each/all")
    public ModelAndView eachAll() {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Map<Integer, List<User>>> maps = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Map<Integer, List<User>> map = new HashMap<>();
            for (int j = 0; j < 2; j++) {
                ArrayList<User> users = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    User user = new User();
                    user.setId(100 + i + j + k);
                    user.setNick("张" + i + j + k);
                    user.setPhone("1390000000" + i + j + k);
                    user.setAddress("甘肃省兰州市" + i + j + k);
                    users.add(user);
                }
                map.put(j, users);
            }
            maps.add(map);
        }
        modelAndView.addObject("maps", maps);
        modelAndView.setViewName("eachAll");
        return modelAndView;
    }
}
