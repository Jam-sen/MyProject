package com.ys.crm.workbench.service;

import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.domain.ActivityRemark;
import com.ys.crm.workbench.vo.PaginationVO;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    Map<String, Boolean> save(Activity activity);

    PaginationVO<Activity> pageList(Activity activity, String pageNum, String pageSize);

    boolean deleteActivity(String[] param);

    Map<String, Object> getUserListAndActivity(String id);

    String updateActivity(Activity activity);

    Activity getActivityDetailById(String id);

    List<ActivityRemark> getRemarkListByAid(String id);

    String deleteRemark(String id);

    Map<String, Object> saveRemark(String noteContent, String activityId,String createName);

    Map<String, Object> updateActivityRemark(String remarkId, String noteContent,String editBy);

    List<Activity> getActivityListByClueId(String clueId);

    List<Activity> getActivityByName(String aname, String clueId);

    List<Activity> searchByName(String name);
}
