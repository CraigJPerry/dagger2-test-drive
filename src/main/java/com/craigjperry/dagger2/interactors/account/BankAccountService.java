package com.craigjperry.dagger2.interactors.account;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.interactors.account.error.BankAccountNotFoundException;

public interface BankAccountService {
    BankAccount openNewAccount();
    BankAccount getAccount(String accountId) throws BankAccountNotFoundException;
    BankAccount appendTransaction(String accountId, Transaction transaction) throws BankAccountTransactionValidationException, BankAccountNotFoundException;
}
