package com.learning.accountsservice.util;

import com.learning.accountsservice.dto.AccountsDTO;
import com.learning.accountsservice.entity.Accounts;

public interface AccountsMapper {
    public static AccountsDTO converToAccountDTO(Accounts accounts, AccountsDTO accountsDTO){
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    public static Accounts convertToAccounts(Accounts accounts, AccountsDTO accountsDTO){
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }
}
