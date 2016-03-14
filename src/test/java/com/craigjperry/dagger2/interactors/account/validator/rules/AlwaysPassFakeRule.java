package com.craigjperry.dagger2.interactors.account.validator.rules;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.transaction.Transaction;

public class AlwaysPassFakeRule implements AccountTransactionValidationRule {

    public static AccountTransactionValidationRule alwaysPassFakeRule() {
        return new AlwaysPassFakeRule();
    }

    private AlwaysPassFakeRule(){}

    @Override
    public void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException {
    }
}
