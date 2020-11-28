package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.Customer;

import java.util.List;

public interface CustomerDao {

    int createByClue(Customer clue);

    Customer getCustomerByName(String company);

    List<Customer> getCustomerName(String name);

    int createByTran(Customer customer);
}
