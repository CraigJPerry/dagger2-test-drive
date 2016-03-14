package com.craigjperry.dagger2.interfaceexternal.application;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.AccountModule;
import com.craigjperry.dagger2.interactors.account.BankAccountService;
import com.craigjperry.dagger2.interactors.account.error.BankAccountNotFoundException;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.interfaceexternal.datastorage.DataStorageModule;
import dagger.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

public class BankCli {

    @Singleton
    @Component(modules = {AccountModule.class, DataStorageModule.class})
    interface BankComponent {
        void inject(BankCli application);
    }

    @Inject
    BankAccountService bankAccountService;

    public BankCli() {
        DaggerBankCli_BankComponent.create().inject(this);
    }

    private void goNuts() throws BankAccountTransactionValidationException, BankAccountNotFoundException {
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
        } catch (BankAccountTransactionValidationException | BankAccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}
