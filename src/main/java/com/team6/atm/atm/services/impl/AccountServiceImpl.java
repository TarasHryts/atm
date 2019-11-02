package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.services.AccountService;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    @Override
    public Optional<Account> create(Account account) {
        return Optional.empty();
    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public void transfer(Account fromAccount, Account toAccount, Long amount) {

    }

    @Override
    public Optional<Account> update(Account account) {
        return Optional.empty();
    }
}
