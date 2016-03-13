package com.craigjperry.dagger2.features;

import com.craigjperry.dagger2.account.BankAccountService;
import com.craigjperry.dagger2.account.DaggerBankAccountServiceComponent;
import cucumber.api.junit.Cucumber;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import org.junit.runner.RunWith;

import javax.inject.Singleton;

@RunWith(Cucumber.class)
public class RunAcceptanceIT {

    @Module
    static class TestingBankModule {
        @Provides
        BankAccountService provideBankAccountService() {
            return DaggerBankAccountServiceComponent.create().bankAccountService();
        }
    }

    @Singleton
    @Component(modules = TestingBankModule.class)
    interface TestingBankComponent {
        void inject(TransactionProcessorSteps transactionProcessorSteps);
    }
}
