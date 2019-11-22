package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.AtmDto;
import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.dto.DepositMoneyInAtmDto;
import com.team6.atm.atm.dto.WithdrawMoneyDto;
import com.team6.atm.atm.dto.util.AtmDtoUtil;
import com.team6.atm.atm.dto.util.BanknotesDtoUtil;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.services.AccountService;
import com.team6.atm.atm.services.AtmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
@Api(value = "ATM", description = "Operations")
public class AtmController {
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
    public void createAtm(@RequestBody List<Banknotes> banknotes) {
        Atm atm = AtmDtoUtil.createAtmFromDto(new AtmDto(banknotes));
        atmService.create(atm);
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
    public void deposit(@RequestBody DepositMoneyInAtmDto depositMoneyInAtmDto) {
        List<Banknotes> banknotesList = new ArrayList<>();
        for (BanknotesDto banknote :depositMoneyInAtmDto.getBanknotesList()) {
            banknotesList.add(BanknotesDtoUtil.createBanknotesFromDto(banknote));
        }
        Account account = accountService.getById(depositMoneyInAtmDto.getAccountId()).orElseThrow(
                () -> new AccountNotFoundException("Account not found."));
        Atm atm = atmService.getAtmById(depositMoneyInAtmDto.getAtmId()).orElseThrow();
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
    public void withdraw(@RequestBody WithdrawMoneyDto withdrawMoneyDto) {
        Account account = accountService.getById(withdrawMoneyDto.getAccountId()).orElseThrow(
                () -> new AccountNotFoundException("Account not found."));
        Atm atm = atmService.getAtmById(withdrawMoneyDto.getAtmId()).orElseThrow();
        atmService.withdraw(atm, account, withdrawMoneyDto.getAmount());
    }
}
