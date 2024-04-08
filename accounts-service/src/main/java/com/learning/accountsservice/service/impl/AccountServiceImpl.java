package com.learning.accountsservice.service.impl;

import com.learning.accountsservice.dto.AccountsDTO;
import com.learning.accountsservice.dto.CustomerDTO;
import com.learning.accountsservice.entity.Accounts;
import com.learning.accountsservice.entity.Customer;
import com.learning.accountsservice.exception.CustomerAlreadyExistsException;
import com.learning.accountsservice.exception.ResourceNotFoundException;
import com.learning.accountsservice.repository.AccountRepository;
import com.learning.accountsservice.repository.CustomerRepository;
import com.learning.accountsservice.service.IAccountService;
import com.learning.accountsservice.util.AccountConstants;
import com.learning.accountsservice.util.AccountsMapper;
import com.learning.accountsservice.util.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(customerOptional.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists for given mobile number "+ customerDTO.getMobileNumber());
        }
        Customer customer = CustomerMapper.convertToCustomer(new Customer(), customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAcocunt(savedCustomer));
    }

    @Override
    public CustomerDTO fetchCustomerDetails(String mobileNum) {
        Customer customer = customerRepository.findByMobileNumber(mobileNum).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNum)
        );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "mobileNumber", mobileNum)
        );
        CustomerDTO customerDTO = CustomerMapper.converToCustomerDTO(customer, new CustomerDTO());
        AccountsDTO accountsDTO = AccountsMapper.converToAccountDTO(accounts, new AccountsDTO());
        customerDTO.setAccountsDTO(accountsDTO);
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDto) {
        boolean isUpdated = false;
        AccountsDTO accountsDto = customerDto.getAccountsDTO();
        if(accountsDto !=null ){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.convertToAccounts(accounts, accountsDto);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.convertToCustomer(customer, customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAcocunt(Customer customer){
        Accounts newAccount = new Accounts();
        long accountNumber = new Random().nextLong();
        newAccount.setAccountType(AccountConstants.SAVINGS.message);
        newAccount.setAccountNumber(accountNumber);
        newAccount.setBranchAddress(AccountConstants.ADDRESS.message);
        newAccount.setCustomerId(customer.getCustomerId());
        return newAccount;
    }
}
