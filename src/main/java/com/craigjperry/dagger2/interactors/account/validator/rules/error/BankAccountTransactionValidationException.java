package com.craigjperry.dagger2.interactors.account.validator.rules.error;

public class BankAccountTransactionValidationException extends Exception {
    public BankAccountTransactionValidationException(String message) {
        super(message);
    }
}
