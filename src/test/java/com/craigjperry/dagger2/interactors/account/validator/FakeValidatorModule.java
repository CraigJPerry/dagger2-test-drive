package com.craigjperry.dagger2.interactors.account.validator;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static com.craigjperry.dagger2.interactors.account.validator.FakeAccountTransactionValidationEngine.alwaysPass;

@Module
public class FakeValidatorModule {
    @Singleton
    @Provides
    static AccountTransactionValidator provideFakeValidationEngine() {
        return alwaysPass();
    }
}
