package com.craigjperry.dagger2.application;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.BankAccountService;
import com.craigjperry.dagger2.account.DaggerBankAccountServiceComponent;
import com.craigjperry.dagger2.account.error.BankAccountNotAvailableException;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.Transaction;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

public class BankCli {

    @Module
    static class BankModule {
        @Provides
        BankAccountService provideBankAccountService() {
            return DaggerBankAccountServiceComponent.create().bankAccountService();
        }
    }

    @Singleton
    @Component(modules = BankModule.class)
    interface BankComponent {
        void inject(BankCli application);
    }

    @Inject
    BankAccountService bankAccountService;

    public BankCli() {
        DaggerBankCli_BankComponent.create().inject(this);
    }

    private void goNuts() throws BankAccountTransactionValidationException, BankAccountNotAvailableException {
        BankAccount bankAccount = bankAccountService.openNewAccount();
        Transaction add10 = Transaction.builder().withDestinationAccountCode(bankAccount.getAccountId()).withAmount(10L).build();
        bankAccountService.appendTransaction(bankAccount.getAccountId(), add10);
        System.out.println(bankAccount);
        bankAccount = bankAccountService.getAccount(bankAccount.getAccountId());
        System.out.println(bankAccount);
    }

    public static void main(String[] args) {
        BankCli bankCli = new BankCli();
        try {
            bankCli.goNuts();
        } catch (BankAccountTransactionValidationException | BankAccountNotAvailableException e) {
            e.printStackTrace();
        }
    }
}
