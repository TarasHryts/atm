package com.team6.atm.atm.repository;

import com.team6.atm.atm.entity.Banknotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanknotesRepository extends JpaRepository<Banknotes, Long> {
}
