package com.ys.crm.settings.service.impl;

import com.ys.crm.settings.dao.DicTypeDao;
import com.ys.crm.settings.dao.DicValueDao;
import com.ys.crm.settings.domain.DicType;
import com.ys.crm.settings.domain.DicValue;
import com.ys.crm.settings.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "DicService")
public class DicServiceImpl implements DicService {
    @Autowired
    private DicTypeDao dicTypeDao;
    @Autowired
    private DicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> getAll() {
        List<DicType> list = dicTypeDao.getTypeList();
        Map<String, List<DicValue>> map = new HashMap<>();
        for (DicType dt : list) {
            List<DicValue> dicValueList = dicValueDao.getDicValueByType(dt.getCode());
            map.put(dt.getCode(), dicValueList);
        }
        return map;
    }
}
