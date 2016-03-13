package com.craigjperry.dagger2.account.validator.rules;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.transaction.Transaction;

public class SufficientFundsRule implements AccountTransactionValidationRule {

    public static SufficientFundsRule sufficientFundsRule() {
        return new SufficientFundsRule();
    }

    private SufficientFundsRule(){}

    @Override

    public void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException {
        if (a.getBalance() + t.amount() < 0) {
            throw new BankAccountTransactionValidationException("Rejecting transaction [%s] to avoid negative balance in account [%s]", t, a);
        }
    }
}
