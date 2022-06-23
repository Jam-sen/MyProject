package com.ys.callable.dao;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface CallableDao {
    ArrayList<HashMap<String, Object>> selectXlbbByPhone(String phone);

    ArrayList<HashMap<String, Object>> selectXlbbByAddressAndDate(String address, String startDate, String endDate);
}
