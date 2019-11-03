package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.services.BanknotesService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BanknotesServiceImpl implements BanknotesService {
    @Override
    public Optional<Banknotes> create(Banknotes banknotes) {
        return Optional.empty();
    }

    @Override
    public Optional<Banknotes> update(Banknotes banknotes) {
        return Optional.empty();
    }

    @Override
    public void delete(Banknotes banknotes) {

    }
}
