package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.AccountDto;
import com.team6.atm.atm.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AccountDtoUtil {
    private static final Logger logger = Logger.getLogger(AccountDtoUtil.class);

    public Account createAccountFromDto(AccountDto accountDto) {
        logger.info("Create account from DTO");
        Account account = new Account();
        account.setBalance(accountDto.getBalance());
        account.setPin(accountDto.getPin());
        return account;
    }
}
