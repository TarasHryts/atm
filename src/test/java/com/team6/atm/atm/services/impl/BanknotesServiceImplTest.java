package com.team6.atm.atm.services.impl;

import static org.mockito.Mockito.when;

import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.repository.BanknotesRepository;
import com.team6.atm.atm.services.BanknotesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BanknotesServiceImplTest {
    private BanknotesService banknotesService;
    private BanknotesRepository banknotesRepository;
    private Banknotes banknotes;

    @Before
    public void setUp() throws Exception {
        banknotesRepository = Mockito.mock(BanknotesRepository.class);
        banknotesService = new BanknotesServiceImpl(banknotesRepository);
        banknotes = new Banknotes(100L, 1L);
    }

    @Test
    public void createOk() {
        when(banknotesRepository.save(banknotes)).thenReturn(banknotes);
        Assert.assertEquals(banknotes, banknotesService.create(banknotes).get());
    }

    @Test
    public void updateOk() {
        when(banknotesRepository.saveAndFlush(banknotes)).thenReturn(banknotes);
        banknotes.setAmount(2L);
        banknotesService.update(banknotes);
        Assert.assertEquals(Long.valueOf(2L), banknotes.getAmount());
    }

    @Test
    public void deleteOk() {

    }
}