package com.team6.atm.atm.services;

import com.team6.atm.atm.entity.Banknotes;
import java.util.Optional;

public interface BanknotesService {
    Optional<Banknotes> create(Banknotes banknotes);

    Optional<Banknotes> update(Banknotes banknotes);

    void delete(Banknotes banknotes);
}
