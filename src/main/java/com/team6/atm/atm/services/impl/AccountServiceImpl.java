package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.repository.AccountRepository;
import com.team6.atm.atm.services.AccountService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> getById(Long accountId) {
        return Optional.empty();
    }

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
