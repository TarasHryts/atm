package com.team6.atm.atm.dto;

import com.team6.atm.atm.entity.Banknotes;

public class BanknotesDtoUtil {
    public static Banknotes createBanknotesFromDto(BanknotesDto banknotesDto){
        Banknotes banknotes = new Banknotes();
        banknotes.setValue(banknotesDto.getValue());
        banknotes.setAmount(banknotesDto.getAmount());
        return banknotes;
    }
}
