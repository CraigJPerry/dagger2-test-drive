package com.craigjperry.dagger2.interactors.account;

import com.craigjperry.dagger2.interactors.account.validator.AccountTransactionValidator;
import com.craigjperry.dagger2.interactors.account.validator.FakeValidatorModule;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = FakeValidatorModule.class)
public class TestAccountModule {
    @Singleton
    @Provides
    static BankAccountService provideBankAccountService(BankAccountRepository bankAccountRepository, AccountTransactionValidator accountTransactionValidator) {
        return new PrimitiveBankAccountService(bankAccountRepository, accountTransactionValidator);
    }
}
