package com.ys.crm.settings.dao;

import com.ys.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getDicValueByType(String code);
}
