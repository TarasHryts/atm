package com.team6.atm.atm.controller;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.services.AccountService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public void add(@RequestBody Account account) {
        accountService.create(account);
    }

    @GetMapping("/{accountId}")
    public Account getById(@PathVariable("accountId") Long accountId) {
        return accountService.getById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account with ID " + accountId + " not found."));
    }

    @PutMapping("/{accountId}")
    public Account update(@PathVariable("accountId") Long accountId,
                          @RequestBody Account newAccount) {
        return accountService.update(accountId, newAccount).orElseThrow(
                () -> new AccountNotFoundException("Account with ID " + accountId + " not found."));
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam("from_account_id") Long fromAccountId,
                         @RequestParam("to_account_id") Long toAccountId,
                         @RequestParam("amount") Long amount) {
        Optional<Account> fromAccount = accountService.getById(fromAccountId);
        Optional<Account> toAccount = accountService.getById(toAccountId);
        if (fromAccount.isPresent() && toAccount.isPresent()) {
            accountService.transfer(fromAccount.get(), toAccount.get(), amount);
        } else {
            throw new AccountNotFoundException("Account not found.");
        }
    }

    @DeleteMapping("/{accountId}")
    public void delete(@PathVariable("accountId") Long accountId) {
        accountService.delete(getById(accountId));
    }
}
