package com.team6.atm.atm.exception;

public class NotEnoughMoneyInAtmException extends RuntimeException {
    public NotEnoughMoneyInAtmException(String message) {
        super(message);
    }
}
