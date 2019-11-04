package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.services.AtmService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmService {
    @Override
    public void deposit(Account account, List<Banknotes> banknotes) {

    }

    @Override
    public void withdraw(Account account, Long amount) {

    }
}
