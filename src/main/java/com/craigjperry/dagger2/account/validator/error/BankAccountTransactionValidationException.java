package com.craigjperry.dagger2.account.validator.error;

public class BankAccountTransactionValidationException extends Exception {
    public BankAccountTransactionValidationException(String message) {
        super(message);
    }

    public BankAccountTransactionValidationException(String format, Object... parameters) {
        this(String.format(format, parameters));
    }
}
