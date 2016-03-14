package com.craigjperry.dagger2.interactors.account.validator.rules;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.transaction.Transaction;

public final class SufficientFundsRule implements AccountTransactionValidationRule {

    public static SufficientFundsRule sufficientFundsRule() {
        return new SufficientFundsRule();
    }

    private SufficientFundsRule(){}

    @Override
    public void validate(BankAccount account, Transaction transaction) throws BankAccountTransactionValidationException {
        if (account.getBalance() + transaction.getAmount() < 0) {
            throw new BankAccountTransactionValidationException(String.format("Insufficient funds in account [%s] for transaction [%s]", account, transaction));
        }
    }
}
