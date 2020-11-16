package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.vo.PaginationVO;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    Integer save(Activity activity);

    List<Activity> getActivityList(Activity activity);

    Integer getTotal(Activity activity);

    int deleteActivity(String[] param);

    Activity getActivityById(String id);
}
