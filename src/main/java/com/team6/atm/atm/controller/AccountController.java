package com.team6.atm.atm.controller;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public Account transaction(@PathVariable("from_account_id") Long fromAccount,
                               @PathVariable("to_account_id") Long toAccount,
                               @PathVariable("amount") Long amount) {
        accountService.transfer(accountService.getById(fromAccount).get(),
                accountService.getById(toAccount).get(), amount);
        return accountService.getById(fromAccount).get();
    }
    // TODO made transaction between accounts
    // TODO create account
    // TODO delete account
    // TODO update account
    // TODO made transaction between accounts  (unsuccess)
    // TODO create account  (unsuccess)
    // TODO delete account  (unsuccess)
    // TODO update account  (unsuccess)
    //
}
