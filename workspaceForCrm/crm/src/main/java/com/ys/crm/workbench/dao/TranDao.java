package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranDao {

    int createByClue(Tran tran);

    int save(Tran tran);

    Tran detail(String tranId);

    int changeStage(Tran tran);

    int getTotal();

    List<Map<String, Object>> getCharts();
}
