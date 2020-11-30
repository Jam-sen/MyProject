package com.ys.crm.workbench.service.impl;

import com.ys.crm.exception.TranException;
import com.ys.crm.util.DateTimeUtil;
import com.ys.crm.util.UUIDUtil;
import com.ys.crm.workbench.dao.CustomerDao;
import com.ys.crm.workbench.dao.TranDao;
import com.ys.crm.workbench.dao.TranHistoryDao;
import com.ys.crm.workbench.domain.Customer;
import com.ys.crm.workbench.domain.Tran;
import com.ys.crm.workbench.domain.TranHistory;
import com.ys.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TranServiceImpl implements TranService {
    @Autowired
    private TranDao tranDao;
    @Autowired
    private TranHistoryDao tranHistoryDao;
    @Autowired
    private CustomerDao customerDao;

    @Override
    public boolean save(Tran tran, String customerName, String createName) throws TranException {
        Customer customer = customerDao.getCustomerByName(customerName);
        tran.setId(UUIDUtil.getUUID());
        tran.setCreateTime(DateTimeUtil.getSysTime());
        tran.setCreateBy(createName);
        if (customer != null) {
            tran.setCustomerId(customer.getId());
        } else {
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setName(customerName);
            customer.setCreateBy(createName);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setNextContactTime(tran.getNextContactTime());
            customer.setOwner(tran.getOwner());
            int count = customerDao.createByTran(customer);
            if (count != 1) {
                throw new TranException("添加失败");
            }
            tran.setCustomerId(customer.getId());
        }
        int count1 = tranDao.save(tran);
        if (count1 != 1) {
            throw new TranException("添加失败");
        }
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setCreateBy(createName);
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        int count2 = tranHistoryDao.create(tranHistory);
        if (count2 != 1) {
            throw new TranException("添加失败");
        }
        return true;
    }

    @Override
    public Tran detail(String tranId) {
        Tran tran = tranDao.detail(tranId);
        return tran;
    }

    @Override
    public List<TranHistory> getHistoryListByTranId(String tranId) {
        return tranHistoryDao.getHistoryListByTranId(tranId);
    }

    @Override
    public boolean changeStage(Tran tran, String userName) {
        int count = tranDao.changeStage(tran);
        if (count != 1) {
            return false;
        }
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setCreateBy(userName);
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        int count2 = tranHistoryDao.save(tranHistory);
        if (count2 != 1) {
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getCharts() {
        int total = tranDao.getTotal();
        List<Map<String ,Object>> list = tranDao.getCharts();
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", list);
        map.put("total", total);
        return map;

    }
}