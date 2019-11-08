package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.repository.AccountRepository;
import com.team6.atm.atm.services.AccountService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> getById(Long accountId) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Account> create(Account account) {
        Account saveAccount = accountRepository.save(account);
        return Optional.ofNullable(saveAccount);
    }

    @Transactional
    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transfer(Account fromAccount, Account toAccount, Long amount) {
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.saveAndFlush(fromAccount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.saveAndFlush(toAccount);
    }

    @Transactional
    @Override
    public Optional<Account> update(Long accountId, Account account) {
        Account saveAndFlush = accountRepository.saveAndFlush(account);
        return Optional.ofNullable(saveAndFlush);
    }
}
