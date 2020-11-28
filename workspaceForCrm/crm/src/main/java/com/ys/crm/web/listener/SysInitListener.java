package com.ys.crm.web.listener;

import com.ys.crm.settings.domain.DicValue;
import com.ys.crm.settings.service.DicService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;


public class SysInitListener implements ServletContextListener {
    @Override
    //该方法用来监听上下文域对象的方法，当服务器启动，上下文域对象创建独享创建完毕后，会执行该方法
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        ApplicationContext beans = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        DicService dicService = (DicService) beans.getBean("DicService");
        Map<String,List<DicValue>> map = dicService.getAll();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            List<DicValue> list= map.get(key);
            application.setAttribute(key,list);
        }

        //读取阶段与成交可能性的properties文件，并放入application中
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Stage2Possibility");
        Enumeration<String> keys = resourceBundle.getKeys();
        Map<String, String> map1 = new HashMap<>();
        while (keys.hasMoreElements()) {
            //阶段
            String key = keys.nextElement();
            //可能性
            String value = resourceBundle.getString(key);
            map1.put(key, value);
        }
        application.setAttribute("pMap",map1);
    }
}
