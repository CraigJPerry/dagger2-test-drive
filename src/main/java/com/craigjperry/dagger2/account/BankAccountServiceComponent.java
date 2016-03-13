package com.craigjperry.dagger2.account;

import dagger.Component;

@Component(modules = AccountModule.class)
public interface BankAccountServiceComponent {
    BankAccountService bankAccountService();
}
