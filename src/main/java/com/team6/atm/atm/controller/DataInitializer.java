package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.AccountDto;
import com.team6.atm.atm.dto.AtmDto;
import com.team6.atm.atm.dto.BanknotesDto;
import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.dto.util.AccountDtoUtil;
import com.team6.atm.atm.dto.util.AtmDtoUtil;
import com.team6.atm.atm.dto.util.BanknotesDtoUtil;
import com.team6.atm.atm.dto.util.UserDtoUtil;
import com.team6.atm.atm.entity.Account;
import com.team6.atm.atm.entity.Atm;
import com.team6.atm.atm.entity.Banknotes;
import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.services.AccountService;
import com.team6.atm.atm.services.AtmService;
import com.team6.atm.atm.services.BanknotesService;
import com.team6.atm.atm.services.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BanknotesService banknotesService;
    @Autowired
    private AtmService atmService;
    @Autowired
    private AccountDtoUtil accountDtoUtil;

    @PostConstruct
    public void initializeData() {
        saveData();
    }

    private void saveData() {
        User userPetro = UserDtoUtil
                .createUserFromDto(new UserDto("Petro", "kjk@kj.jdd", "1", "1"));
        userService.create(userPetro);
        Account account = accountDtoUtil.createAccountFromDto(new AccountDto(10000L, "1111"));
        Set<Account> userPetroAccountList = userPetro.getAccountList();
        userPetroAccountList.add(account);
        userPetro.setAccountList(userPetroAccountList);
        accountService.create(account);

        User userIvan = UserDtoUtil
                .createUserFromDto(new UserDto("Ivan", "iven@kj.jdd", "1", "1"));
        userService.create(userIvan);
        Banknotes banknotesOneHundred = BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(100L, 50L));
        banknotesService.create(banknotesOneHundred);
        Banknotes banknotesTwoHundred = BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(200L, 50L));
        banknotesService.create(banknotesTwoHundred);
        Banknotes banknotesFiveHundred = BanknotesDtoUtil
                .createBanknotesFromDto(new BanknotesDto(500L, 50L));
        banknotesService.create(banknotesFiveHundred);
        List<Banknotes> banknotesList = new ArrayList<>();
        banknotesList.add(banknotesOneHundred);
        banknotesList.add(banknotesTwoHundred);
        banknotesList.add(banknotesFiveHundred);
        Atm atm = AtmDtoUtil.createAtmFromDto(new AtmDto(banknotesList));
        atmService.create(atm);
    }
}
