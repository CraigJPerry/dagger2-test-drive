package com.craigjperry.dagger2.account.validator.rules;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.Transaction;

public class AlwaysPassFakeRule implements AccountTransactionValidationRule {

    public static AlwaysPassFakeRule alwaysPassFakeRule() {
        return new AlwaysPassFakeRule();
    }

    private AlwaysPassFakeRule(){}

    @Override
    public void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException {
    }
}
