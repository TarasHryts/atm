package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.dto.BanknotesDtoUtil;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.services.AccountService;
import com.team6.atm.atm.services.AtmService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
public class AtmController {
    private static final Long ATM_ID = 0L;

    @Autowired
    private AtmService atmService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public void deposit(@RequestParam("account_id") Long accountId,
                        @RequestBody BanknotesDto oneHundred,
                        @RequestBody BanknotesDto twoHundred,
                        @RequestBody BanknotesDto fiveHundred) {
        List<Banknotes> banknotes = new ArrayList<>();
        banknotes.add(BanknotesDtoUtil.getBanknotesFromDto(oneHundred));
        banknotes.add(BanknotesDtoUtil.getBanknotesFromDto(twoHundred));
        banknotes.add(BanknotesDtoUtil.getBanknotesFromDto(fiveHundred));
        Account account = accountService.getById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account with ID " + accountId + " not found."));
        Atm atm = atmService.getAtmById(ATM_ID);
        atmService.deposit(atm, account, banknotes);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam("account_id") Long accountId,
                         @RequestParam("amount") Long amount) {
        Account account = accountService.getById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account with ID " + accountId + " not found."));
        Atm atm = atmService.getAtmById(ATM_ID);
        atmService.withdraw(atm, account, amount);
    }
}
