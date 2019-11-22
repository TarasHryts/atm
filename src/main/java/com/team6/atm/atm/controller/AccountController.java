package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.AccountDto;
import com.team6.atm.atm.dto.TransferDto;
import com.team6.atm.atm.dto.util.AccountDtoUtil;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.exception.AccountNotFoundException;
import com.team6.atm.atm.services.AccountService;
import com.team6.atm.atm.services.UserService;
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
    public void transfer(@RequestBody TransferDto transferDto) {
        Optional<Account> fromAccount = accountService.getById(transferDto.getFromAccount());
        Optional<Account> toAccount = accountService.getById(transferDto.getToAccount());
        accountService.transfer(fromAccount.get(), toAccount.get(), transferDto.getAmount());
    }

    @DeleteMapping("/{accountId}")
    public void delete(@PathVariable("accountId") Long accountId) {
        accountService.delete(getById(accountId));
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
