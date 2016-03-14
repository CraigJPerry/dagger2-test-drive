package com.craigjperry.dagger2.interfaceexternal.datastorage;

import com.craigjperry.dagger2.interactors.account.BankAccountRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DataStorageModule {
    @Singleton
    @Provides
    static BankAccountRepository provideInMemoryBankAccountRepository() {
        return new InMemoryBankAccountRepository();
    }
}
