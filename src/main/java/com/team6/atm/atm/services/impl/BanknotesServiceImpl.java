package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.repository.BanknotesRepository;
import com.team6.atm.atm.services.BanknotesService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BanknotesServiceImpl implements BanknotesService {

    @Autowired
    private BanknotesRepository banknotesRepository;

    @Transactional
    @Override
    public Optional<Banknotes> create(Banknotes banknotes) {
        Banknotes saveBanknotes = banknotesRepository.save(banknotes);
        return Optional.ofNullable(saveBanknotes);
    }

    @Transactional
    @Override
    public Optional<Banknotes> update(Banknotes banknotes) {
        Banknotes newBanknotes = banknotesRepository.save(banknotes);
        return Optional.ofNullable(newBanknotes);
    }

    @Transactional
    @Override
    public void delete(Banknotes banknotes) {
        banknotesRepository.delete(banknotes);
    }
}
