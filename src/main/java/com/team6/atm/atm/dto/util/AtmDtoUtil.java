package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.AtmDto;
import com.team6.atm.atm.entity.Atm;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AtmDtoUtil {
    public static Atm createAtmFromDto(AtmDto atmDto) {
        Atm atm = new Atm();
        atm.setBanknotesList(atmDto.getBanknotesList());
        return atm;
    }
}
