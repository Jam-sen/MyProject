package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.Tran;

public interface TranDao {

    int createByClue(Tran tran);

    int save(Tran tran);

    Tran detail(String tranId);

    int changeStage(Tran tran);
}
