package com.ys.crm.workbench.web.controller;

import com.ys.crm.exception.LoginException;
import com.ys.crm.settings.domain.User;
import com.ys.crm.settings.service.UserService;
import com.ys.crm.util.DateTimeUtil;
import com.ys.crm.util.JacksonUtil;
import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.domain.ActivityRemark;
import com.ys.crm.workbench.service.ActivityService;
import com.ys.crm.workbench.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        return userService.getUserList();
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

    @ResponseBody
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public void deleteActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] param = request.getParameterValues("id");
        String json = JacksonUtil.flagJson(activityService.deleteActivity(param));
        response.getWriter().write(json);

    }

    @ResponseBody
    @RequestMapping(value = "/getUserListAndActivity.do")
    public Map<String, Object> getUserListAndActivity(String id) {
        return activityService.getUserListAndActivity(id);
    }


    @RequestMapping(value = "/update.do")
    public void updateActivity(HttpServletRequest request, HttpServletResponse response, Activity activity) throws IOException {
        activity.setEditTime(DateTimeUtil.getSysTime());
        activity.setEditBy(((User) request.getSession().getAttribute("user")).getName());
        String flag = activityService.updateActivity(activity);
        response.getWriter().write(flag);
    }

    @RequestMapping(value = "/detail.do")
    public ModelAndView detail(String id) {
        Activity activity = activityService.getActivityDetailById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activity", activity);
        modelAndView.setViewName("/workbench/activity/detail.jsp");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getRemarkListByAid.do")
    public List<ActivityRemark> getRemarkListByAid(String id) {
        System.out.println(1234);
        return activityService.getRemarkListByAid(id);

    }

    @RequestMapping(value = "/deleteRemark.do", method = RequestMethod.POST)
    public void deleteRemark(String id, HttpServletResponse response) throws IOException {
        String flag = activityService.deleteRemark(id);
        response.getWriter().write(flag);
    }

    @ResponseBody
    @RequestMapping(value = "/saveRemark.do", method = RequestMethod.POST)
    public Map<String, Object> saveRemark(String noteContent, String activityId, HttpServletRequest request) {
        String createName = ((User) request.getSession().getAttribute("user")).getName();
        return activityService.saveRemark(noteContent, activityId, createName);
    }

    @ResponseBody
    @RequestMapping(value = "/updateRemark.do", method = RequestMethod.POST)
    public Map<String, Object> updateRemark(String remarkId, String noteContent, HttpServletRequest request) {
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        return activityService.updateActivityRemark(remarkId, noteContent, editBy);
    }
}
