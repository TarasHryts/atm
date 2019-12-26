package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.repository.AccountRepository;
import com.team6.atm.atm.services.AccountService;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
    @Autowired
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> getById(Long accountId) {
        logger.info(this.getClass().getName() + " get account");
        return accountRepository.findById(accountId);
    }

    @Transactional
    @Override
    public Optional<Account> create(Account account) {
        logger.info(this.getClass().getName() + " create account");
        Account saveAccount = accountRepository.save(account);
        return Optional.ofNullable(saveAccount);
    }

    @Transactional
    @Override
    public void delete(Account account) {
        logger.info(this.getClass().getName() + " delete account");
        accountRepository.delete(account);
    }

    @Transactional
    @Override
    public void transfer(Account fromAccount, Account toAccount, Long amount) {
        logger.info(this.getClass().getName() + " transfer money");

        if (fromAccount == null || toAccount == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.saveAndFlush(fromAccount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.saveAndFlush(toAccount);
    }

    @Transactional
    @Override
    public Optional<Account> update(Long accountId, Account account) {
        logger.info(this.getClass().getName() + " update account");
        Account saveAndFlush = accountRepository.saveAndFlush(account);
        return Optional.ofNullable(saveAndFlush);
    }
}
