package com.team6.atm.atm.services;

import com.team6.atm.atm.entity.Account;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getById(Long accountId);

    Optional<Account> create(Account account);

    void delete(Account account);

    void transfer(Account fromAccount, Account toAccount, Long amount);

    Optional<Account> update(Long accountId, Account account);
}
