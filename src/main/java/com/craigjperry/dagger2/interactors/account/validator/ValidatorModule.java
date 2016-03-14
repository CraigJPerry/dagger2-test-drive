package com.craigjperry.dagger2.interactors.account.validator;

import com.craigjperry.dagger2.interactors.account.validator.rules.AccountTransactionValidationRule;
import com.google.common.collect.ImmutableList;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static com.craigjperry.dagger2.interactors.account.validator.rules.MatchingDestinationRule.matchingDestinationRule;
import static com.craigjperry.dagger2.interactors.account.validator.rules.SufficientFundsRule.sufficientFundsRule;

@Module
public class ValidatorModule {
    @Singleton
    @Provides
    static ImmutableList<AccountTransactionValidationRule> provideValidationRules() {
        return ImmutableList.of(
                matchingDestinationRule(),
                sufficientFundsRule()
        );
    }

    @Singleton
    @Provides
    static AccountTransactionValidator provideValidationEngine(ImmutableList<AccountTransactionValidationRule> validationRules) {
        return new AccountTransactionValidationEngine(validationRules);
    }
}
