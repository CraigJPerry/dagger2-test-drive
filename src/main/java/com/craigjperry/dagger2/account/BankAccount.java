package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.transaction.Transaction;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import static com.google.common.base.Preconditions.*;

@AutoValue
public abstract class BankAccount {
    Optional<Long> cachedBalance = Optional.absent();

    static BankAccount create(Long accountId) {
        checkArgument(accountId > 0L, "Negative accountId [%s] not permitted", accountId);
        return new AutoValue_BankAccount(accountId, ImmutableList.<Transaction>of());
    }

    static BankAccount createWithTransactions(Long accountId, ImmutableList<Transaction> transactions) {
        checkArgument(accountId > 0L, "Negative accountId [%s] not permitted", accountId);
        checkNotNull(transactions, "Invalid transactions list");
        for (Transaction transaction : transactions) {
            checkState(accountId.equals(transaction.sourceAccount()) || accountId.equals(transaction.destinationAccount()),
                    "Invalid transaction [%s] doesn't belong to this account [%s]", transaction, accountId);
        }
        return new AutoValue_BankAccount(accountId, transactions);
    }

    public abstract Long accountId();

    public abstract ImmutableList<Transaction> transactions();

    public Long getBalance() {
        if (!cachedBalance.isPresent()) {
            cachedBalance = Optional.of(sumTotal());
        }
        return cachedBalance.get();  // Long is immutable, safe to share reference
    }

    private long sumTotal() {
        long total = 0;
        for (Transaction transaction : transactions()) {
            total += transaction.amount();
        }
        return total;
    }
}
