package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int getCountByAids(String[] param);

    int deleteByAids(String[] param);

    List<ActivityRemark> getRemarkListByAid(String id);

    int deleteById(String id);

    int saveRemark(ActivityRemark ar);

    int updateRemark(ActivityRemark activityRemark);
}
