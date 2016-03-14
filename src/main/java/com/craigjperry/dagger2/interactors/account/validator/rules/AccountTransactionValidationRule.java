package com.craigjperry.dagger2.interactors.account.validator.rules;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.transaction.Transaction;

public interface AccountTransactionValidationRule {
    void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException;
}
