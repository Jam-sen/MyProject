package com.ys.crm.workbench.dao;

public interface ActivityRemarkDao {
    int getCountByAids(String[] param);

    int deleteByAids(String[] param);
}
