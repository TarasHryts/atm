package com.team6.atm.atm.services;

import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import java.util.List;

public interface AtmService {
    void deposit(Atm atm, Account account, List<Banknotes> banknotes);
    void withdraw(Atm atm,Account account, Long amount);
}
