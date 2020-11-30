package com.ys.crm.workbench.service;

import com.ys.crm.exception.TranException;
import com.ys.crm.workbench.domain.Tran;
import com.ys.crm.workbench.domain.TranHistory;

import java.util.List;
import java.util.Map;

public interface TranService {
    boolean save(Tran tran, String customerName, String createName) throws TranException;

    Tran detail(String tranId);

    List<TranHistory> getHistoryListByTranId(String tranId);

    boolean changeStage(Tran tran, String userName);

    Map<String, Object> getCharts();
}
