package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.account.validator.AccountTransactionValidationEngine;
import com.craigjperry.dagger2.datastorage.Repository;
import dagger.Module;
import dagger.Provides;

import static com.craigjperry.dagger2.account.validator.rules.MatchingDestinationRule.matchingDestinationRule;
import static com.craigjperry.dagger2.account.validator.rules.SufficientFundsRule.sufficientFundsRule;

@Module
class AccountModule {
    @Provides
    static Repository<String, BankAccount> provideBankAccountRepository() {
        return new InMemoryBankAccountRepository();
    }

    @Provides
    static AccountTransactionValidationEngine provideAccountTransactionValidationEngine() {
        return AccountTransactionValidationEngine.builder()
                .withRule(matchingDestinationRule())
                .withRule(sufficientFundsRule())
                .build();
    }
}
