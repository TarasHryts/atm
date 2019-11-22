package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.AtmDto;
import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.dto.util.AtmDtoUtil;
import com.team6.atm.atm.dto.util.BanknotesDtoUtil;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.exception.IncorrectAmountException;
import com.team6.atm.atm.exception.NotEnoughMoneyException;
import com.team6.atm.atm.exception.NotEnoughMoneyInAtmException;
import com.team6.atm.atm.services.AccountService;
import com.team6.atm.atm.services.AtmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
@Api(value = "ATM", description = "Operations")
public class AtmController {
    private static final Long SMALLEST_DENOMINATION = 100L;
    @Autowired
    private AtmService atmService;

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "add", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404,
                    message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/add")
    public void createAtm(@RequestParam("hundred") Long amountOne,
                          @RequestParam("twoHundred") Long amountTwo,
                          @RequestParam("fiveHundred") Long amountFive) {
        List<Banknotes> banknotes = new ArrayList<>();
        banknotes.add(BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(100L, amountOne)));
        banknotes.add(BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(200L, amountTwo)));
        banknotes.add(BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(500L, amountFive)));
        Atm atm = AtmDtoUtil.createAtmFromDto(new AtmDto(banknotes));
        atmService.create(atm);
        System.out.println(atm);
    }

    @ApiOperation(value = "deposit", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404,
                    message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/deposit")
    public void deposit(@Valid @RequestParam("account_id") Long accountId,
                        @RequestParam("atm_id") Long atmId,
                        @RequestParam("hundred") Long amountOne,
                        @RequestParam("twoHundred") Long amountTwo,
                        @RequestParam("fiveHundred") Long amountFive) {
        List<Banknotes> banknotesList = new ArrayList<>();
        banknotesList.add(BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(100L, amountOne)));
        banknotesList.add(BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(200L, amountTwo)));
        banknotesList.add(BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(500L, amountFive)));
        Account account = accountService.getById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account with ID "
                        + accountId + " not found."));
        Atm atm = atmService.getAtmById(atmId).orElseThrow();
        atmService.deposit(atm, account, banknotesList);
    }

    @ApiOperation(value = "withdraw", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/withdraw")
    public void withdraw(@RequestParam("account_id") Long accountId,
                         @RequestParam("atm_id") Long atmId,
                         @RequestParam("amount") Long amount) {
        Account account = accountService.getById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account with ID " + accountId + " not found."));
        Atm atm = atmService.getAtmById(atmId).orElseThrow();
        if (account.getBalance() < amount) {
            throw new NotEnoughMoneyException("Not enough money in your account");
        }
        if (atmService.sumOfMoneyInList(atm.getBanknotesList()) < amount) {
            throw new NotEnoughMoneyInAtmException("Not enough money in ATM");
        }
        if (amount % SMALLEST_DENOMINATION != 0) {
            throw new IncorrectAmountException("Еhe amount should be divided by "
                    + SMALLEST_DENOMINATION);
        }
        atmService.withdraw(atm, account, amount);
    }
}
