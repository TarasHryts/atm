package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.AccountDto;
import com.team6.atm.atm.entity.Account;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AccountDtoUtil {
    public Account createAccountFromDto(AccountDto accountDto) {
        Account account = new Account();
        account.setBalance(accountDto.getBalance());
        account.setPin(accountDto.getPin());
        return account;
    }
}
