package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.exception.IncorrectAmountException;
import com.team6.atm.atm.exception.NotEnoughMoneyException;
import com.team6.atm.atm.exception.NotEnoughMoneyInAtmException;
import com.team6.atm.atm.repository.AccountRepository;
import com.team6.atm.atm.repository.AtmRepository;
import com.team6.atm.atm.repository.BanknotesRepository;
import com.team6.atm.atm.services.AtmService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtmServiceImpl implements AtmService {
    private static final Logger logger = Logger.getLogger(AtmServiceImpl.class);
    private static final Long SMALLEST_DENOMINATION = 100L;
    @Autowired
    private final AtmRepository atmRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final BanknotesRepository banknotesRepository;

    public AtmServiceImpl(AtmRepository atmRepository, AccountRepository accountRepository,
                          BanknotesRepository banknotesRepository) {
        this.atmRepository = atmRepository;
        this.accountRepository = accountRepository;
        this.banknotesRepository = banknotesRepository;
    }

    @Transactional
    @Override
    public Optional<Atm> create(Atm atm) {
        logger.info(this.getClass().getName() + " create atm");
        try {
            atm = atmRepository.save(atm);
        } catch (DataAccessException e) {
            System.out.println(e);
        }
        return Optional.ofNullable(atm);
    }

    @Transactional
    @Override
    public void deposit(Atm atm, Account account, List<Banknotes> banknotes) {
        logger.info(this.getClass().getName() + " account deposit money");
        Long sum = 0L;
        for (Banknotes banknote : banknotes) {
            sum += banknote.getAmount() * banknote.getValue();
        }
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);
        List<Banknotes> addBanknotes = addBanknotes(banknotes, atm.getBanknotesList());
        atm.setBanknotesList(addBanknotes);
        atmRepository.save(atm);
    }

    @Transactional
    @Override
    public void withdraw(Atm atm, Account account, Long amount) {
        logger.info(this.getClass().getName() + " account withdraw money");
        if (account.getBalance() < amount) {
            throw new NotEnoughMoneyException("Not enough money in your account");
        }
        if (sumOfMoneyInList(atm.getBanknotesList()) < amount) {
            throw new NotEnoughMoneyInAtmException("Not enough money in ATM");
        }
        if (amount % SMALLEST_DENOMINATION != 0) {
            throw new IncorrectAmountException("Еhe amount should be divided by "
                    + SMALLEST_DENOMINATION);
        }
        List<Banknotes> banknotesList = atm.getBanknotesList()
                .stream().sorted()
                .collect(Collectors.toList());
        atm.setBanknotesList(takeMoneyFromAtm(banknotesList, amount));
        account.setBalance(account.getBalance() - amount);
        atmRepository.save(atm);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public Optional<Atm> getAtmById(Long atmId) {
        logger.info(this.getClass().getName() + " get atm with id=" + atmId);
        return atmRepository.findById(atmId);
    }

    private List<Banknotes> addBanknotes(
            List<Banknotes> banknotesListClient, List<Banknotes> banknotesListInAtm) {
        for (Banknotes banknotesAtm : banknotesListInAtm) {
            for (Banknotes banknotesClient : banknotesListClient) {
                if (banknotesAtm.getValue().equals(banknotesClient.getValue())) {
                    banknotesAtm.setAmount(banknotesAtm.getAmount() + banknotesClient.getAmount());
                }
            }
        }
        return banknotesListInAtm;
    }

    List<Banknotes> takeMoneyFromAtm(List<Banknotes> banknotesList, Long sumOfWithdraw) {
        List<Banknotes> banknotes = new ArrayList<>();
        for (int i = 0; i < banknotesList.size(); i++) {
            if (sumOfWithdraw / banknotesList.get(i).getValue() > banknotesList.get(i)
                    .getAmount()) {
                sumOfWithdraw = sumOfWithdraw - banknotes.get(i)
                        .getAmount() * banknotes.get(i).getValue();
                banknotes.add(new Banknotes(banknotesList.get(i).getValue(), 0L));
            } else {
                banknotes.add(new Banknotes(banknotesList.get(i).getValue(),
                        banknotesList.get(i).getAmount()
                                - sumOfWithdraw / banknotesList.get(i).getValue()));
                sumOfWithdraw = sumOfWithdraw % banknotesList.get(i).getValue();
            }
        }
        return banknotes;
    }

    public Long sumOfMoneyInList(List<Banknotes> banknotes) {
        logger.info(this.getClass().getName() + " count sum of money in list");
        return banknotes.stream().mapToLong(x -> x.getAmount() * x.getValue()).sum();
    }
}
