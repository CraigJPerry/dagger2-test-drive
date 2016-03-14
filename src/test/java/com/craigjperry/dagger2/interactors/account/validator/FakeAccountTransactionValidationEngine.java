package com.craigjperry.dagger2.interactors.account.validator;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.validator.rules.AccountTransactionValidationRule;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.google.common.collect.ImmutableList;

import static com.craigjperry.dagger2.interactors.account.validator.rules.AlwaysFailFakeRule.alwaysFailFakeRule;
import static com.craigjperry.dagger2.interactors.account.validator.rules.AlwaysPassFakeRule.alwaysPassFakeRule;

public class FakeAccountTransactionValidationEngine extends AccountTransactionValidationEngine {

    public static FakeAccountTransactionValidationEngine alwaysPass() {
        ImmutableList<AccountTransactionValidationRule> alwaysPassValidator = ImmutableList.of(alwaysPassFakeRule());
        return new FakeAccountTransactionValidationEngine(alwaysPassValidator);
    }

    public static FakeAccountTransactionValidationEngine alwaysFail() {
        ImmutableList<AccountTransactionValidationRule> alwaysFailValidator = ImmutableList.of(alwaysFailFakeRule());
        return new FakeAccountTransactionValidationEngine(alwaysFailValidator);
    }

    FakeAccountTransactionValidationEngine(ImmutableList<AccountTransactionValidationRule> validationRules) {
        super(validationRules);
    }

    @Override
    public void validate(BankAccount account, Transaction transaction) throws BankAccountTransactionValidationException {
        super.validate(account, transaction);
    }
}
