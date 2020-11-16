package com.ys.crm.workbench.web.controller;

import com.ys.crm.exception.LoginException;
import com.ys.crm.settings.domain.User;
import com.ys.crm.settings.service.UserService;
import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.service.ActivityService;
import com.ys.crm.workbench.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUserList.do", method = RequestMethod.GET)
    public List<User> getUserList(HttpServletRequest request, HttpServletResponse response) throws LoginException, IOException {
        List<User> userList = userService.getUserList();
        return userList;
    }

    @ResponseBody
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public Map<String, Boolean> save(Activity activity, HttpServletRequest request) {
        activity.setCreateBy(((User) request.getSession().getAttribute("user")).getName());
        return activityService.save(activity);
    }

    @ResponseBody
    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    public PaginationVO<Activity> pageList(Activity activity, String pageNum, String pageSize) {
        return activityService.pageList(activity, pageNum, pageSize);
    }

    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    public void deleteActivity(String[] param) {
        System.out.println(param);
    }
}
