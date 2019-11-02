package com.team6.atm.atm.entity;

public class Banknotes {
    private Long billId;
    private Long value;
    private Long amount;

    public Banknotes(Long value, Long amount) {
        this.value = value;
        this.amount = amount;
    }
}
