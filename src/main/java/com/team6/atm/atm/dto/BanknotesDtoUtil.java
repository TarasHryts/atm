package com.team6.atm.atm.dto;

import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.entity.Banknotes;

public class BanknotesDtoUtil {
    public static Banknotes getBanknotesFromDto(BanknotesDto banknotesDto) {
        Banknotes banknotes = new Banknotes();
        banknotes.setValue((banknotes.getValue()));
        banknotes.setAmount(banknotes.getAmount());
        return banknotes;
    }
}
