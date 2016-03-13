package com.craigjperry.dagger2.account.validator.rules;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.transaction.Transaction;

public class AlwaysFailFakeRule implements AccountTransactionValidationRule {

    public static AlwaysFailFakeRule alwaysFailFakeRule() {
        return new AlwaysFailFakeRule();
    }

    private AlwaysFailFakeRule(){}

    @Override
    public void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException {
        throw new BankAccountTransactionValidationException("Always Fail Fake Rule");
    }
}
