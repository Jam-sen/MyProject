package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationDao {
    int unBund(String id);

    List<ClueActivityRelation> getListByClueId(String clueId);

    int deleteByClueId(String clueId);
}
