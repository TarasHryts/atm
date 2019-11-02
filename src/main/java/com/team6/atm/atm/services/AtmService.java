package com.team6.atm.atm.services;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Banknotes;
import java.util.List;

public interface AtmService {
    void deposit(Account account, List<Banknotes> banknotes);
    void withdraw(Account account, Long amount);
}
