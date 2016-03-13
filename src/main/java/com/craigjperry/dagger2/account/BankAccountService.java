package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.account.error.BankAccountNotAvailableException;
import com.craigjperry.dagger2.account.validator.AccountTransactionValidationEngine;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.datastorage.Repository;
import com.craigjperry.dagger2.entities.Transaction;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class BankAccountService {
    private final Repository<String, BankAccount> bankAccountRepository;
    private final AccountTransactionValidationEngine validationEngine;

    @Inject
    public BankAccountService(Repository<String, BankAccount> bankAccountRepository, AccountTransactionValidationEngine validationEngine) {
        this.bankAccountRepository = bankAccountRepository;
        this.validationEngine = validationEngine;
    }

    public BankAccount openNewAccount() {
        return bankAccountRepository.add();
    }

    public BankAccount getAccount(String accountId) throws BankAccountNotAvailableException {
        checkNotNull(accountId, "Invalid accountId");
        Optional<BankAccount> maybeAccount = bankAccountRepository.find(accountId);
        if (maybeAccount.isPresent()) {
            return maybeAccount.get();
        }
        throw new BankAccountNotAvailableException("accountId not found");
    }

    public BankAccount appendTransaction(String accountId, Transaction t) throws BankAccountTransactionValidationException, BankAccountNotAvailableException {
        checkNotNull(t, "Invalid transaction");
        BankAccount account = getAccount(accountId);
        validationEngine.validate(account, t);
        return persistTransaction(account, t);
    }

    private BankAccount persistTransaction(BankAccount a, Transaction t) {
        ImmutableList<Transaction> appendedTransaction = ImmutableList.<Transaction>builder()
                .addAll(a.getTransactions())
                .add(t)
                .build();
        return bankAccountRepository.update(BankAccount.builder()
                .withAccountId(a.getAccountId())
                .withTransactions(appendedTransaction)
                .build());
    }
}
