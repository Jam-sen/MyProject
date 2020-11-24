package com.ys.crm.workbench.dao;


import com.ys.crm.workbench.domain.Clue;
import com.ys.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueDao {

    int saveClue(Clue clue);

    Clue getDetailById(String id);


    int bund(ClueActivityRelation clueActivityRelation) ;

    Clue getClueById(String clueId);

    int deleteById(String clueId);

    List<Clue> pageList();

    int getTotal();

}
