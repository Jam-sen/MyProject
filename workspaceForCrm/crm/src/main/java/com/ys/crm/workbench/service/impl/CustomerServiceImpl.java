package com.ys.crm.workbench.service.impl;

import com.ys.crm.workbench.dao.CustomerDao;
import com.ys.crm.workbench.dao.CustomerRemarkDao;
import com.ys.crm.workbench.domain.Customer;
import com.ys.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerRemarkDao customerRemarkDao;

    @Override
    public List<Customer> getCustomerName(String name) {
        return customerDao.getCustomerName(name);
    }
}
