package com.ys.crm.workbench.service.impl;

import com.github.pagehelper.PageHelper;
import com.ys.crm.util.DateTimeUtil;
import com.ys.crm.util.UUIDUtil;
import com.ys.crm.workbench.dao.ActivityDao;
import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.service.ActivityService;
import com.ys.crm.workbench.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Transactional
    @Override
    public Map<String, Boolean> save(Activity activity) {
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        Integer num = activityDao.save(activity);
        Map<String, Boolean> map = new HashMap<>();
        if (num > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @Override
    @Transactional
    public PaginationVO<Activity> pageList(Activity activity, String pageNum, String pageSize) {

        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        List<Activity> list = activityDao.getActivityList(activity);
        PaginationVO<Activity> paginationVO = new PaginationVO<>();
        paginationVO.setDataList(list);
        Integer total = activityDao.getTotal(activity);
        paginationVO.setTotal(total);
        return paginationVO;
    }
}
