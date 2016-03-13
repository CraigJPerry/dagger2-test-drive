package com.craigjperry.dagger2.account.validator.rules;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.transaction.Transaction;

public class MatchingDestinationRule implements AccountTransactionValidationRule {

    public static MatchingDestinationRule matchingDestinationRule() {
        return new MatchingDestinationRule();
    }

    private MatchingDestinationRule() {}

    @Override
    public void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException {
        if (a.accountId().equals(t.destinationAccount())) {
            return;
        }
        throw new BankAccountTransactionValidationException("Rejecting transaction [%s] not destined for account [%s]", t, a);
    }
}
