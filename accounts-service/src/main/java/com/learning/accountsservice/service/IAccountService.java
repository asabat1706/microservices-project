package com.learning.accountsservice.service;

import com.learning.accountsservice.dto.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);

    public CustomerDTO fetchCustomerDetails(String mobileNum);

    boolean updateAccount(CustomerDTO customerDto);

    boolean deleteAccount(String mobileNumber);
}
