package com.craigjperry.dagger2.interactors.account.validator.rules;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.transaction.Transaction;

public final class MatchingDestinationRule implements AccountTransactionValidationRule {

    public static MatchingDestinationRule matchingDestinationRule() {
        return new MatchingDestinationRule();
    }

    private MatchingDestinationRule() {}

    @Override
    public void validate(BankAccount account, Transaction transaction) throws BankAccountTransactionValidationException {
        if (account.getAccountId().equals(transaction.getDestinationAccountCode())) {
            return;
        }
        throw new BankAccountTransactionValidationException(String.format("Transaction [%s] not for account [%s]", transaction, account));
    }
}
