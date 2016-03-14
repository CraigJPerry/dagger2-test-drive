package com.craigjperry.dagger2.interactors.account.validator;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.validator.rules.AccountTransactionValidationRule;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.google.common.collect.ImmutableList;

import javax.inject.Inject;

class AccountTransactionValidationEngine implements AccountTransactionValidator {
    private final ImmutableList<AccountTransactionValidationRule> validationRules;

    @Inject
    AccountTransactionValidationEngine(ImmutableList<AccountTransactionValidationRule> validationRules) {
        this.validationRules = checkNotNullOrEmpty(validationRules);
    }

    private static ImmutableList<AccountTransactionValidationRule> checkNotNullOrEmpty(ImmutableList<AccountTransactionValidationRule> validationRules) {
        if (validationRules == null) {
            throw new NullPointerException("validationRules");
        } else if (validationRules.size() == 0) {
            throw new IllegalArgumentException("validationRules 0 sized");
        }
        return validationRules;
    }

    @Override
    public void validate(BankAccount account, Transaction transaction) throws BankAccountTransactionValidationException {
        for (AccountTransactionValidationRule rule : validationRules) {
            rule.validate(account, transaction);
        }
    }
}
