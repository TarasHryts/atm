package com.team6.atm.atm.services;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import java.util.List;
import java.util.Optional;

public interface AtmService {
    void deposit(Atm atm, Account account, List<Banknotes> banknotes);

    void withdraw(Atm atm, Account account, Long amount);

    Optional<Atm> getAtmById(Long atmId);
}
