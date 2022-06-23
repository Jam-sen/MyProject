package com.ys.callable.service;

import com.ys.callable.domain.ResValue;

import java.util.ArrayList;
import java.util.HashMap;

public interface CallableService {
    ResValue<ArrayList<HashMap<String, Object>>> searchBigData(String phone, String address, String startDate, String endDate);
}
