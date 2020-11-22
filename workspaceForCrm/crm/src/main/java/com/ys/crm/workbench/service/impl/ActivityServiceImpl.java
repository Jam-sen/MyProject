package com.ys.crm.workbench.service.impl;

import com.github.pagehelper.PageHelper;
import com.ys.crm.settings.dao.UserDao;
import com.ys.crm.settings.domain.User;
import com.ys.crm.util.DateTimeUtil;
import com.ys.crm.util.JacksonUtil;
import com.ys.crm.util.UUIDUtil;
import com.ys.crm.workbench.dao.ActivityDao;
import com.ys.crm.workbench.dao.ActivityRemarkDao;
import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.domain.ActivityRemark;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivityRemarkDao activityRemarkDao;

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

    @Override
    @Transactional
    public boolean deleteActivity(String[] param) {
        boolean flag = true;
        //查询出需要删除的备注的数量
        int count1 = activityRemarkDao.getCountByAids(param);
        //删除备注，返回受到影响的条数
        int count2 = activityRemarkDao.deleteByAids(param);
        if (count1 != count2) {
            flag = false;
        }
        //删除市场活动
        int count3 = activityDao.deleteActivity(param);
        if (count3 != param.length) {
            flag=false;
        }
        return flag;
    }

    @Override
    @Transactional
    public Map<String, Object> getUserListAndActivity(String id) {
        Activity activity = activityDao.getActivityById(id);
        List<User> list = userDao.getUserList();
        Map<String, Object> map = new HashMap<>();
        map.put("activity", activity);
        map.put("userList", list);
        return map;
    }

    @Override
    @Transactional
    public String updateActivity(Activity activity) {
        int count = activityDao.updateActivity(activity);
        if (count == 1) {
            return JacksonUtil.flagJson(true);
        }
        return JacksonUtil.flagJson(false);
    }

    @Override
    @Transactional
    public Activity getActivityDetailById(String id) {
        return activityDao.getActivityDetailById(id);
    }

    @Override
    @Transactional
    public List<ActivityRemark> getRemarkListByAid(String id) {
        return activityRemarkDao.getRemarkListByAid(id);
    }

    @Override
    @Transactional
    public String deleteRemark(String id) {
        int count = activityRemarkDao.deleteById(id);
        if (count == 1) {
            return JacksonUtil.flagJson(true);
        }
        return JacksonUtil.flagJson(false);
    }

    @Override
    @Transactional
    public Map<String, Object> saveRemark(String noteContent, String activityId,String createName) {
        ActivityRemark ar = new ActivityRemark();
        ar.setId(UUIDUtil.getUUID());
        ar.setCreateBy(createName);
        ar.setCreateTime(DateTimeUtil.getSysTime());
        ar.setEditFlag("0");
        ar.setNoteContent(noteContent);
        ar.setActivityId(activityId);
        int count = activityRemarkDao.saveRemark(ar);
        Map<String, Object> map = new HashMap<>();
        if (count == 1) {
            map.put("flag", true);
            map.put("ar", ar);
        } else {
            map.put("flag", false);
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> updateActivityRemark(String remarkId, String noteContent,String editBy) {
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(remarkId);
        activityRemark.setNoteContent(noteContent);
        activityRemark.setEditBy(editBy);
        activityRemark.setEditFlag("1");
        activityRemark.setEditTime(DateTimeUtil.getSysTime());
        int count = activityRemarkDao.updateRemark(activityRemark);
        Map<String, Object> map = new HashMap<>();
        if (count == 1) {
            map.put("ar", activityRemark);
            map.put("flag", true);
        } else {
            map.put("flag", false);
        }
        return map;
    }

    @Override
    public List<Activity> getActivityListByClueId(String clueId) {
        return activityDao.getActivityByClueId(clueId);
    }

    @Override
    public List<Activity> getActivityByName(String aname, String clueId) {
        List<Activity> activityList = activityDao.getActivityByName(aname, clueId);
        for (Activity a :
                activityList) {
            System.out.println(a);
        }
        return activityList;
    }

    @Override
    public List<Activity> searchByName(String name) {
        return activityDao.searchByName(name);
    }


}