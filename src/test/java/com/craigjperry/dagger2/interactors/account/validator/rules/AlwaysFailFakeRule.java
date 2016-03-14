package com.craigjperry.dagger2.interactors.account.validator.rules;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.transaction.Transaction;

public class AlwaysFailFakeRule implements AccountTransactionValidationRule {

    public static AccountTransactionValidationRule alwaysFailFakeRule() {
        return new AlwaysFailFakeRule();
    }

    private AlwaysFailFakeRule(){}

    @Override
    public void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException {
        throw new BankAccountTransactionValidationException("Always Fail Fake Rule");
    }
}
