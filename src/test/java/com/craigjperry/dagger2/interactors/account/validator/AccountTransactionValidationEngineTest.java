package com.craigjperry.dagger2.interactors.account.validator;

import com.craigjperry.dagger2.interactors.account.validator.rules.AccountTransactionValidationRule;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static com.craigjperry.dagger2.interactors.account.validator.FakeAccountTransactionValidationEngine.alwaysFail;
import static com.craigjperry.dagger2.interactors.account.validator.FakeAccountTransactionValidationEngine.alwaysPass;

public class AccountTransactionValidationEngineTest {
    @Test(expected = BankAccountTransactionValidationException.class)
    public void validatorThrowsExceptionOnFailingRule() throws Exception {
        AccountTransactionValidationEngine validationEngine = alwaysFail();
        validationEngine.validate(null, null);
    }

    @Test()
    public void noExceptionWhenValidationPasses() throws Exception {
        AccountTransactionValidationEngine validationEngine = alwaysPass();
        validationEngine.validate(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyValidatorListIsIllegalState() throws Exception {
        new AccountTransactionValidationEngine(ImmutableList.<AccountTransactionValidationRule>of());
    }
}
