package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.AtmDto;
import com.team6.atm.atm.entity.Atm;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AtmDtoUtil {
    private static final Logger logger = Logger.getLogger(AtmDtoUtil.class);

    public static Atm createAtmFromDto(AtmDto atmDto) {
        logger.info("Create atm from DTO");
        Atm atm = new Atm();
        atm.setBanknotesList(atmDto.getBanknotesList());
        return atm;
    }
}
