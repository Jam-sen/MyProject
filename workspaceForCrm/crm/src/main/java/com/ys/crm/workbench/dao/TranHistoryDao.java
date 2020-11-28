package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.TranHistory;

import java.util.List;

public interface TranHistoryDao {

    int create(TranHistory tranHistory);

    List<TranHistory> getHistoryListByTranId(String tranId);

    int save(TranHistory tranHistory);
}
