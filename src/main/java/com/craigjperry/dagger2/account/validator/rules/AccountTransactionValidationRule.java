package com.craigjperry.dagger2.account.validator.rules;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.Transaction;

public interface AccountTransactionValidationRule {
    void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException;
}
