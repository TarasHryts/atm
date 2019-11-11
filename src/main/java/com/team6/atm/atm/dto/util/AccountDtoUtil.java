package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.AccountDto;
import com.team6.atm.atm.entity.Account;

public class AccountDtoUtil {
    public static Account createAccountFromDto(AccountDto accountDto) {
        Account account = new Account();
        account.setUser(accountDto.getUser());
        account.setBalance(accountDto.getBalance());
        account.setPin(accountDto.getPin());
        return account;
    }
}
