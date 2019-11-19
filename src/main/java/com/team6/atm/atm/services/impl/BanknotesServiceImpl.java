package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.repository.BanknotesRepository;
import com.team6.atm.atm.services.BanknotesService;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BanknotesServiceImpl implements BanknotesService {
    private static final Logger logger = Logger.getLogger(BanknotesServiceImpl.class);
    @Autowired
    private final BanknotesRepository banknotesRepository;

    public BanknotesServiceImpl(BanknotesRepository banknotesRepository) {
        this.banknotesRepository = banknotesRepository;
    }

    @Transactional
    @Override
    public Optional<Banknotes> create(Banknotes banknotes) {
        logger.info(this.getClass().getName() + " create banknote");
        Banknotes saveBanknotes = banknotesRepository.save(banknotes);
        return Optional.ofNullable(saveBanknotes);
    }

    @Transactional
    @Override
    public Optional<Banknotes> update(Banknotes banknotes) {
        logger.info(this.getClass().getName() + " update banknote");
        Banknotes newBanknotes = banknotesRepository.save(banknotes);
        return Optional.ofNullable(newBanknotes);
    }

    @Transactional
    @Override
    public void delete(Banknotes banknotes) {
        logger.info(this.getClass().getName() + " delete banknote");
        banknotesRepository.delete(banknotes);
    }
}
