package com.craigjperry.dagger2.interfaceexternal.application;

import com.craigjperry.dagger2.interactors.account.BankAccountRepository;
import com.craigjperry.dagger2.interactors.account.BankAccountService;
import com.craigjperry.dagger2.interactors.account.TestAccountModule;
import com.craigjperry.dagger2.interfaceexternal.datastorage.TestDataStorageModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TestAccountModule.class, TestDataStorageModule.class})
public interface TestBankComponent {
    BankAccountService makeTestBankAccountService();
    BankAccountRepository makeTestBankAccountRepository();
}
