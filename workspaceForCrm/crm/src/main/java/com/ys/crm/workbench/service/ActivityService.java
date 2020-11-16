package com.ys.crm.workbench.service;

import com.ys.crm.workbench.domain.Activity;
import com.ys.crm.workbench.vo.PaginationVO;

import java.util.Map;

public interface ActivityService {

    Map<String, Boolean> save(Activity activity);

    PaginationVO<Activity> pageList(Activity activity, String pageNum, String pageSize);

    boolean deleteActivity(String[] param);

    Map<String, Object> getUserListAndActivity(String id);
}
