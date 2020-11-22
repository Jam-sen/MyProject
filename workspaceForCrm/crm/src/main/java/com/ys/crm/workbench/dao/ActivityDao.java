package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDao {
    Integer save(Activity activity);

    List<Activity> getActivityList(Activity activity);

    Integer getTotal(Activity activity);

    int deleteActivity(String[] param);

    Activity getActivityById(String id);


    int updateActivity(Activity activity);

    Activity getActivityDetailById(String id);

    List<Activity> getActivityByClueId(String clueId);

    List<Activity> getActivityByName(@Param("aname") String aname,@Param("clueId") String clueId);

    List<Activity> searchByName(String name);
}
