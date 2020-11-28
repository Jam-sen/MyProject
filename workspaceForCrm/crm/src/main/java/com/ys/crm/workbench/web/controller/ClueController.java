package com.ys.crm.workbench.web.controller;

import com.ys.crm.exception.ConvertException;
import com.ys.crm.settings.domain.User;
import com.ys.crm.settings.service.UserService;
import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.domain.Clue;
import com.ys.crm.workbench.domain.Tran;
import com.ys.crm.workbench.service.ActivityService;
import com.ys.crm.workbench.service.ClueService;
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
@RequestMapping(value = "/clue")
public class ClueController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ClueService clueService;

    @ResponseBody
    @RequestMapping(value = "/getUserList.do", method = RequestMethod.GET)
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @RequestMapping(value = "/saveClue.do", method = RequestMethod.POST)
    public void saveClue(Clue clue, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        clue.setCreateBy(createBy);
        String flag = clueService.saveClue(clue);
        response.getWriter().write(flag);
    }

    @RequestMapping(value = "/detail.do")
    public ModelAndView detail(String id) {
        Clue clue = clueService.getDetailById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("d", clue);
        modelAndView.setViewName("/workbench/clue/detail.jsp");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getActivityListByClueId.do")
    public List<Activity> getActivityListByClueId(String clueId) {
        System.out.println("123");
        return activityService.getActivityListByClueId(clueId);
    }

    @RequestMapping(value = "/unbund.do")
    public void unBund(String id, HttpServletResponse response) throws IOException {
        String flag = clueService.unBund(id);
        response.getWriter().write(flag);
    }

    @ResponseBody
    @RequestMapping(value = "/searchActivity.do")
    public List<Activity> getActivityByName(String aname, String clueId) {
        return activityService.getActivityByName(aname, clueId);
    }

    @RequestMapping(value = "/bund.do")
    public void bund(HttpServletResponse response, String ids, String clueId) throws IOException {
        String flag = clueService.bund(ids, clueId);
        response.getWriter().write(flag);
    }

    @ResponseBody
    @RequestMapping(value = "/searchByName.do")
    public List<Activity> searchByName(String name) {
        return activityService.searchByName(name);
    }

    @RequestMapping(value = "/convert.do")
    public String convert(String clueId, String isCreateTran, Tran tran, HttpServletRequest request) throws ConvertException {
        User user = ((User) request.getSession().getAttribute("user"));
        clueService.convert(clueId, tran, isCreateTran,user);
        return "/workbench/clue/index.jsp";
    }

    @ResponseBody
    @RequestMapping("/pageList.do")
    public Map<String, Object> pageList(String pageNum, String pageSize) {
        return  clueService.pageList(pageNum, pageSize);
    }

}
