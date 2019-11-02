package com.team6.atm.atm.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private Set<Account> accountList = new HashSet<>();
    private Set<Role> roles = new HashSet<>();
}
