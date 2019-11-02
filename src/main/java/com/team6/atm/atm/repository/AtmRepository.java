package com.team6.atm.atm.repository;

import com.team6.atm.atm.entity.Atm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<Atm, Long> {
}
