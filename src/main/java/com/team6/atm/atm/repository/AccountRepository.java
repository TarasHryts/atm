package com.team6.atm.atm.repository;

import com.team6.atm.atm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
