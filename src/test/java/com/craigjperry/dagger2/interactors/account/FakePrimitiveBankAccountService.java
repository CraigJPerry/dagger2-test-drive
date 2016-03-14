package com.craigjperry.dagger2.interactors.account;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.error.BankAccountNotFoundException;
import com.craigjperry.dagger2.interactors.account.validator.AccountTransactionValidator;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;

public class FakePrimitiveBankAccountService extends PrimitiveBankAccountService {

    FakePrimitiveBankAccountService(BankAccountRepository bankAccountRepository, AccountTransactionValidator validationEngine) {
        super(bankAccountRepository, validationEngine);
    }

    @Override
    public BankAccount getAccount(String accountId) throws BankAccountNotFoundException {
        throw new BankAccountNotFoundException("FakePrimitiveBankAccountService");
    }

    @Override
    public BankAccount appendTransaction(String accountId, Transaction transaction) throws BankAccountTransactionValidationException, BankAccountNotFoundException {
        throw new BankAccountTransactionValidationException("FakePrimitiveBankAccountService");
    }
}
