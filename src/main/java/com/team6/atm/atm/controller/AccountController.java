package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.AccountDto;
import com.team6.atm.atm.dto.TransferDto;
import com.team6.atm.atm.dto.util.AccountDtoUtil;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.services.AccountService;
import com.team6.atm.atm.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    @Autowired
    private UserService userService;
    @Autowired
    private AccountDtoUtil accountDtoUtil;

    @PostMapping("/add")
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
    public void add(@RequestParam("userId") Long userId, @RequestBody AccountDto accountDto) {
        Account account = accountDtoUtil.createAccountFromDto(accountDto);
        accountService.create(account);
        User user = userService.getUserById(userId).orElseThrow();
        Set<Account> accountList = user.getAccountList();
        accountList.add(account);
        user.setAccountList(accountList);
        userService.update(userId, user);
    }

    @GetMapping("/{accountId}")
    @ApiOperation(value = "accountId", response = List.class)
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
    public Account getById(@PathVariable("accountId") Long accountId) {
        return accountService.getById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account with ID " + accountId + " not found."));
    }

    @PutMapping("/{accountId}")
    @ApiOperation(value = "accountId", response = List.class)
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
    public Account update(@PathVariable("accountId") Long accountId,
                          @RequestBody Account newAccount) {
        return accountService.update(accountId, newAccount).orElseThrow(
                () -> new AccountNotFoundException("Account with ID " + accountId + " not found."));
    }

    @PostMapping("/transfer")
    @ApiOperation(value = "transfer", response = List.class)
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
    public void transfer(@RequestBody TransferDto transferDto) {
        Optional<Account> fromAccount = accountService.getById(transferDto.getFromAccount());
        Optional<Account> toAccount = accountService.getById(transferDto.getToAccount());
        accountService.transfer(fromAccount.get(), toAccount.get(), transferDto.getAmount());
    }

    @DeleteMapping("/{accountId}")
    @ApiOperation(value = "accountId", response = List.class)
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
    public void delete(@PathVariable("accountId") Long accountId) {
        accountService.delete(getById(accountId));
    }

    @GetMapping("/all")
    @ApiOperation(value = "all", response = List.class)
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
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
