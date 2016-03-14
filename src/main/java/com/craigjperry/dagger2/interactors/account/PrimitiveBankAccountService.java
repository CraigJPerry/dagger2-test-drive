package com.craigjperry.dagger2.interactors.account;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.error.BankAccountNotFoundException;
import com.craigjperry.dagger2.interactors.account.validator.AccountTransactionValidator;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

class PrimitiveBankAccountService implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final AccountTransactionValidator validationEngine;

    @Inject
    PrimitiveBankAccountService(BankAccountRepository bankAccountRepository, AccountTransactionValidator validationEngine) {
        this.bankAccountRepository = checkNotNull(bankAccountRepository, "bankAccountRepository");
        this.validationEngine = checkNotNull(validationEngine, "validationEngine");
    }

    @Override
    public BankAccount openNewAccount() {
        return bankAccountRepository.add();
    }

    @Override
    public BankAccount getAccount(String accountId) throws BankAccountNotFoundException {
        checkNotNull(accountId, "accountId");
        Optional<BankAccount> maybeAccount = bankAccountRepository.find(accountId);
        if (maybeAccount.isPresent()) {
            return maybeAccount.get();
        }
        throw new BankAccountNotFoundException(String.format("accountId [%s]", accountId));
    }

    @Override
    public BankAccount appendTransaction(String accountId, Transaction transaction) throws BankAccountTransactionValidationException, BankAccountNotFoundException {
        checkNotNull(transaction, "transaction");
        BankAccount account = getAccount(accountId);
        validationEngine.validate(account, transaction);
        return persistTransaction(account, transaction);
    }

    private BankAccount persistTransaction(BankAccount account, Transaction transaction) {
        ImmutableList<Transaction> newTransactionList = ImmutableList.<Transaction>builder()
                .addAll(account.getTransactions())
                .add(transaction)
                .build();
        return bankAccountRepository.update(BankAccount.builder()
                .withAccountId(account.getAccountId())
                .withTransactions(newTransactionList)
                .build());
    }
}
