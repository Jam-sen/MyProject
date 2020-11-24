package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkDao {

    List<ClueRemark> getListByClueId(String clueId);

    int deleteByClueId(String clueId);

}
