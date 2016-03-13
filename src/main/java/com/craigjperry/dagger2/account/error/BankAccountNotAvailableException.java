package com.craigjperry.dagger2.account.error;

public class BankAccountNotAvailableException extends Exception {
    public BankAccountNotAvailableException(String message) {
        super(message);
    }
}
