package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.entity.Banknotes;
import org.apache.log4j.Logger;

public class BanknotesDtoUtil {
    private static final Logger logger = Logger.getLogger(BanknotesDtoUtil.class);

    public static Banknotes createBanknotesFromDto(BanknotesDto banknotesDto) {
        logger.info("Create banknotes from DTO");
        Banknotes banknotes = new Banknotes();
        banknotes.setValue(banknotesDto.getValue());
        banknotes.setAmount(banknotesDto.getAmount());
        return banknotes;
    }
}
