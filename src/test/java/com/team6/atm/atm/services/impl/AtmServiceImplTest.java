package com.team6.atm.atm.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.repository.AccountRepository;
import com.team6.atm.atm.repository.AtmRepository;
import com.team6.atm.atm.repository.BanknotesRepository;
import com.team6.atm.atm.services.AtmService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AtmServiceImplTest {
    private AtmRepository atmRepository;
    private AccountRepository accountRepository;
    private BanknotesRepository banknotesRepository;
    private AtmService atmService;
    private Atm atm;
    private Account account;
    private List<Banknotes> banknotesList;

    @Before
    public void setUp() throws Exception {
        accountRepository = Mockito.mock(AccountRepository.class);
        atmRepository = Mockito.mock(AtmRepository.class);
        banknotesRepository = Mockito.mock(BanknotesRepository.class);
        atmService = new AtmServiceImpl(atmRepository, accountRepository, banknotesRepository);
        List<Atm> expectedData = new ArrayList<>();
        banknotesList = new ArrayList<>();
        account = new Account();
        account.setBalance(1300L);
        atm = new Atm();
        Banknotes banknotesOneHundred = new Banknotes(100L, 50L);
        banknotesRepository.save(banknotesOneHundred);
        Banknotes banknotesTwoHundred = new Banknotes(200L, 50L);
        banknotesRepository.save(banknotesTwoHundred);
        Banknotes banknotesFiveHundred = new Banknotes(500L, 50L);
        banknotesRepository.save(banknotesFiveHundred);
        banknotesList.add(banknotesOneHundred);
        banknotesList.add(banknotesTwoHundred);
        banknotesList.add(banknotesFiveHundred);
        atm.setBanknotesList(banknotesList);
        expectedData.add(atm);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createOk() {
        when(atmRepository.save(any())).thenReturn(atm);
        Assert.assertEquals(atm, atmService.create(atm).get());
    }

    @Test
    public void depositOk() {
        List<Banknotes> clientMoney = new ArrayList<>();
        Banknotes banknotesOneHundred = new Banknotes(100L, 1L);
        banknotesRepository.save(banknotesOneHundred);
        Banknotes banknotesTwoHundred = new Banknotes(200L, 2L);
        banknotesRepository.save(banknotesTwoHundred);
        Banknotes banknotesFiveHundred = new Banknotes(500L, 3L);
        banknotesRepository.save(banknotesFiveHundred);
        clientMoney.add(banknotesOneHundred);
        clientMoney.add(banknotesTwoHundred);
        clientMoney.add(banknotesFiveHundred);
        atmService.deposit(atm, account, clientMoney);
        Assert.assertEquals(atm.getBanknotesList().get(0).getValue(), banknotesOneHundred.getValue());
        Assert.assertEquals(atm.getBanknotesList().get(0).getAmount(), Long.valueOf(51L));
        Assert.assertEquals(atm.getBanknotesList().get(1).getValue(), banknotesTwoHundred.getValue());
        Assert.assertEquals(atm.getBanknotesList().get(1).getAmount(), Long.valueOf(52L));
        Assert.assertEquals(atm.getBanknotesList().get(2).getValue(), banknotesFiveHundred.getValue());
        Assert.assertEquals(atm.getBanknotesList().get(2).getAmount(), Long.valueOf(53L));
    }

    @Test
    public void withdrawOk() {
        Long moneyToWithdraw = 1300L;
        atmService.withdraw(atm, account, moneyToWithdraw);
        Assert.assertEquals(account.getBalance(), Long.valueOf(0L));
        Assert.assertEquals(atm.getBanknotesList().get(0).getAmount(), Long.valueOf(48L));
        Assert.assertEquals(atm.getBanknotesList().get(1).getAmount(), Long.valueOf(49L));
        Assert.assertEquals(atm.getBanknotesList().get(2).getAmount(), Long.valueOf(49L));
    }

    @Test
    public void getAtmByIdOk() {
        when(atmRepository.findById(any())).thenReturn(Optional.of(atm));
        Assert.assertEquals(atm, atmService.getAtmById(1L).get());
    }

    @Test
    public void sumOfMoneyInListOk() {
        List<Banknotes> clientMoney = new ArrayList<>();
        Banknotes banknotesOneHundred = new Banknotes(100L, 1L);
        banknotesRepository.save(banknotesOneHundred);
        Banknotes banknotesTwoHundred = new Banknotes(200L, 1L);
        banknotesRepository.save(banknotesTwoHundred);
        Banknotes banknotesFiveHundred = new Banknotes(500L, 2L);
        banknotesRepository.save(banknotesFiveHundred);
        clientMoney.add(banknotesOneHundred);
        clientMoney.add(banknotesTwoHundred);
        clientMoney.add(banknotesFiveHundred);
        Assert.assertEquals(Long.valueOf(1300L), atmService.sumOfMoneyInList(clientMoney));
    }
}