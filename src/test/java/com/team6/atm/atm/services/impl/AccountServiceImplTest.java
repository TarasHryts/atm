package com.team6.atm.atm.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.repository.AccountRepository;
import com.team6.atm.atm.services.AccountService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AccountServiceImplTest {
    private AccountService accountService;
    private AccountRepository accountRepository;
    private Account account;
    private Account account1;
    private List<Account> expectedData;

    @Before
    public void setUp() throws Exception {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountServiceImpl(accountRepository);
        expectedData = new ArrayList<>();
        account = new Account();
        account.setAccountId(2L);
        account.setBalance(5000L);
        account1 = new Account();
        account1.setAccountId(3L);
        account1.setBalance(6000L);
        expectedData.add(account);
        expectedData.add(account1);
    }

    @Test
    public void getAllAccountsOk() {
        when(accountRepository.findAll()).thenReturn(expectedData);
        List<Account> allAccounts = accountService.getAllAccounts();
        Assert.assertFalse(allAccounts.isEmpty());
        Assert.assertEquals(2, allAccounts.size());
    }

    @Test
    public void getByIdOk() {
        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        Assert.assertEquals(account, accountService.getById(2L).get());
        Assert.assertEquals(account.getBalance(), accountService.getById(2L).get().getBalance());
    }

    @Test
    public void createOk() {
        when(accountRepository.save(account)).thenReturn(account);
        Assert.assertEquals(account, accountService.create(account).get());
        Assert.assertEquals(account.getBalance(), accountService.create(account).get().getBalance());
    }

    @Test
    public void deleteOk() {
        Integer sizeBefore = accountRepository.findAll().size();
        accountService.delete(account);
        Integer sizeAfter = accountRepository.findAll().size();
        Assert.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void transferOk() {
        Long sumBefore = account.getBalance() + account1.getBalance();
        accountService.transfer(account, account1, account.getBalance());
        Long sumAfter = account.getBalance() + account1.getBalance();
        Assert.assertEquals(Long.valueOf(0L), account.getBalance());
        Assert.assertEquals(sumBefore, sumAfter);
    }

    @Test
    public void updateOk() {
        when(accountRepository.saveAndFlush(account)).thenReturn(account);
        account.setBalance(222L);
        accountService.update(account.getAccountId(), account);
        Assert.assertEquals(Long.valueOf(222L), account.getBalance());
    }
}