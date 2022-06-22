package com.ys.callable.service.impl;

import com.ys.callable.dao.impl.CallableDao;
import com.ys.callable.domain.ResValue;
import com.ys.callable.service.CallableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

@Slf4j
@Service
public class CallableServiceImpl implements CallableService {
    @Autowired
    private CallableDao callableDao;

    @Override
    public ResValue<Object> searchBigData() {
        log.info(Thread.currentThread().getName()+"业务开始");
        String dateStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("====>进入service"+dateStr);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.yield();
        log.info(Thread.currentThread().getName()+"业务结束");
        return new ResValue<Object>("0","操作成功","success");
    }
}
