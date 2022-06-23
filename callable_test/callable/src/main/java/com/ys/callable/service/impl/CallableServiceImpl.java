package com.ys.callable.service.impl;

import com.ys.callable.dao.CallableDao;
import com.ys.callable.domain.ResValue;
import com.ys.callable.service.CallableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Service
public class CallableServiceImpl implements CallableService {
    @Resource
    private CallableDao callableDao;

    @Override
    public ResValue<ArrayList<HashMap<String, Object>>> searchBigData(String phone, String address, String startDate, String endDate) {
        log.info("查询开始:{phone:"+phone+",address:"+address+",startDate:"+startDate+",endDate:"+endDate+"}");
        ResValue<ArrayList<HashMap<String, Object>>> resValue = new ResValue<>("参数为空","200",null);
        if (phone != null && !phone.isEmpty()) {
            ArrayList<HashMap<String, Object>> list = callableDao.selectXlbbByPhone(phone);
            resValue = new ResValue<>("通过手机号查询", "100", list);
        } else {
            if (address != null && !address.isEmpty() && startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                ArrayList<HashMap<String, Object>> list = callableDao.selectXlbbByAddressAndDate(address, startDate, endDate);
                resValue = new ResValue<>("通过地址和扫码时间段进行查询", "100", list);
            } else {
                resValue.setMsg("参数不全");
            }
        }
        log.info("查询结束");
        return resValue;
    }
}
