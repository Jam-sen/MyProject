package com.ys.crm.workbench.dao;

import com.ys.crm.workbench.domain.Customer;

public interface CustomerDao {

    int createByClue(Customer clue);

    Customer getCustomerByName(String company);
}
