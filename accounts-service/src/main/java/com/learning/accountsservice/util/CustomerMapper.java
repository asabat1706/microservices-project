package com.learning.accountsservice.util;

import com.learning.accountsservice.dto.AccountsDTO;
import com.learning.accountsservice.dto.CustomerDTO;
import com.learning.accountsservice.entity.Accounts;
import com.learning.accountsservice.entity.Customer;

import java.time.LocalDateTime;

public interface CustomerMapper {
    public static CustomerDTO converToCustomerDTO(Customer customer, CustomerDTO customerDTO){
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer convertToCustomer(Customer customer, CustomerDTO customerDTO){
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        return customer;
    }
}
