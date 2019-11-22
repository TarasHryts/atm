package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.AtmDto;
import com.team6.atm.atm.dto.DepositMoneyInAtmDto;
import com.team6.atm.atm.dto.WithdrawMoneyDto;
import com.team6.atm.atm.dto.util.AtmDtoUtil;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.services.AccountService;
import com.team6.atm.atm.services.AtmService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
public class AtmController {
    @Autowired
    private AtmService atmService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public void createAtm(@RequestBody List<Banknotes> banknotes) {
        Atm atm = AtmDtoUtil.createAtmFromDto(new AtmDto(banknotes));
        atmService.create(atm);
    }

    @PostMapping("/deposit")
    public void deposit(@Valid @RequestBody DepositMoneyInAtmDto depositMoneyInAtmDto) {
        Account account = accountService.getById(depositMoneyInAtmDto.getAccountId()).orElseThrow(
                () -> new AccountNotFoundException("Account not found."));
        Atm atm = atmService.getAtmById(depositMoneyInAtmDto.getAtmId()).orElseThrow();
        atmService.deposit(atm, account, depositMoneyInAtmDto.getBanknotesList());
    }

    @PostMapping("/withdraw")
    public void withdraw(@Valid @RequestBody WithdrawMoneyDto withdrawMoneyDto) {
        Account account = accountService.getById(withdrawMoneyDto.getAccountId()).orElseThrow(
                () -> new AccountNotFoundException("Account not found."));
        Atm atm = atmService.getAtmById(withdrawMoneyDto.getAtmId()).orElseThrow();
        atmService.withdraw(atm, account, withdrawMoneyDto.getAmount());
    }
}
