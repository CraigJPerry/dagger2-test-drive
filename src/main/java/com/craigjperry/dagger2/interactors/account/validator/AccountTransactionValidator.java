package com.craigjperry.dagger2.interactors.account.validator;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;

public interface AccountTransactionValidator {
    void validate(BankAccount account, Transaction transaction) throws BankAccountTransactionValidationException;
}
