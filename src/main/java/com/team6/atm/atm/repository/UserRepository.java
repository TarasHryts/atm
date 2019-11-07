package com.team6.atm.atm.repository;

import com.team6.atm.atm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
