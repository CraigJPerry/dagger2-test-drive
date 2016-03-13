package com.craigjperry.dagger2.account.validator;

import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import org.junit.Test;

import static com.craigjperry.dagger2.account.validator.rules.AlwaysFailFakeRule.alwaysFailFakeRule;
import static com.craigjperry.dagger2.account.validator.rules.AlwaysPassFakeRule.alwaysPassFakeRule;

public class AccountTransactionValidationEngineTest {
    @Test(expected = BankAccountTransactionValidationException.class)
    public void validatorThrowsExceptionOnFailingRule() throws Exception {
        AccountTransactionValidationEngine validationEngine = AccountTransactionValidationEngine.builder()
                .withRule(alwaysPassFakeRule())
                .withRule(alwaysFailFakeRule())
                .build();
        validationEngine.validate(null, null);
    }

    @Test()
    public void noExceptionWhenValidationPasses() throws Exception {
        AccountTransactionValidationEngine validationEngine = AccountTransactionValidationEngine.builder()
                .withRule(alwaysPassFakeRule())
                .build();
        validationEngine.validate(null, null);
    }

    @Test(expected = IllegalStateException.class)
    public void emptyValidatorListIsIllegalState() throws Exception {
        AccountTransactionValidationEngine validationEngine = AccountTransactionValidationEngine.builder().build();
        validationEngine.validate(null, null);
    }
}
