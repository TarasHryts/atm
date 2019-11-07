package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.services.AtmService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmService {

    @Override
    public void deposit(Atm atm, Account account, List<BanknotesDto> banknotesDto) {

    }

    @Override
    public void withdraw(Atm atm, Account account, Long amount) {

    }
}
