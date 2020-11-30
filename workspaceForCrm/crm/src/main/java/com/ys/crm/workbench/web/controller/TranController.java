package com.ys.crm.workbench.web.controller;

import com.ys.crm.exception.TranException;
import com.ys.crm.settings.domain.User;
import com.ys.crm.settings.service.UserService;
import com.ys.crm.util.DateTimeUtil;
import com.ys.crm.workbench.domain.Customer;
import com.ys.crm.workbench.domain.Tran;
import com.ys.crm.workbench.domain.TranHistory;
import com.ys.crm.workbench.service.CustomerService;
import com.ys.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/transaction")
public class TranController {
    @Autowired
    private TranService tranService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/add.do")
    public ModelAndView add() {
        List<User> list = userService.getUserList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", list);
        modelAndView.setViewName("/workbench/transaction/save.jsp");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomerName.do")
    public List<Customer> getCustomerName(String name) {
        return customerService.getCustomerName(name);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public String save(Tran tran, String customerName, HttpServletRequest request) throws TranException {
        String createName = ((User) request.getSession().getAttribute("user")).getName();
        boolean flag = tranService.save(tran, customerName, createName);
        if (flag) {
            return "redirect:/workbench/transaction/index.jsp";
        }
        return "redirect:/workbench/transaction/save.jsp";
    }

    @RequestMapping(value = "/detail.do")
    public ModelAndView detail(String tranId, HttpServletRequest request) {
        Tran tran = tranService.detail(tranId);
        /*
         * 处理可能性
         * 阶段tran.stage
         * 阶段和可能性之间的对应关系 pMap
         */
        String stage = tran.getStage();
        ServletContext application = request.getServletContext();
        Map<String, String> pMap = (Map<String, String>) application.getAttribute("pMap");
        String possibility = pMap.get(stage);
        tran.setPossibility(possibility);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tran", tran);
        modelAndView.setViewName("/workbench/transaction/detail.jsp");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getHistoryListByTranId.do")
    public List<TranHistory> getHistoryListByTranId(String tranId, HttpServletRequest request) {
        Map<String, String> map = (Map<String, String>) request.getServletContext().getAttribute("pMap");

        List<TranHistory> list = tranService.getHistoryListByTranId(tranId);
        for (TranHistory tranHistory : list) {
            String stage = tranHistory.getStage();
            tranHistory.setPossibility(map.get(stage));
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/changeStage.do")
    public Map<String, Object> changeStage(Tran tran, HttpServletRequest request) {
        String userName = ((User) request.getSession().getAttribute("user")).getName();
        tran.setEditBy(userName);
        tran.setEditTime(DateTimeUtil.getSysTime());
        boolean flag = tranService.changeStage(tran, userName);
        Map<String, String> pMap = (Map<String, String>) request.getServletContext().getAttribute("pMap");
        String possibility = pMap.get(tran.getStage());
        tran.setPossibility(possibility);
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        map.put("tran", tran);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getCharts.do")
    public Map<String, Object> getCharts() {
        return  tranService.getCharts();
    }
}