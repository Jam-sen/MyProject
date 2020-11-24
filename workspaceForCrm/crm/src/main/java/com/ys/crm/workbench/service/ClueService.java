package com.ys.crm.workbench.service;

import com.ys.crm.exception.ConvertException;
import com.ys.crm.workbench.domain.Clue;
import com.ys.crm.workbench.domain.Tran;

import java.util.Map;

public interface ClueService {
    String saveClue(Clue clue);

    Clue getDetailById(String id);

    String unBund(String id);

    String bund(String ids, String clueId);

    void convert(String clueId, Tran tran, String isCreateTran, String userName) throws ConvertException;

    Map<String, Object> pageList(String pageNum, String pageSize);
}
