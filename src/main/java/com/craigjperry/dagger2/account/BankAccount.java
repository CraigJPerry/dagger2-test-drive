package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.entities.Transaction;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class BankAccount {
    private final String accountId;
    private final ImmutableList<Transaction> transactions;
    private Optional<Long> cachedBalance;

    private BankAccount(String accountId, ImmutableList<Transaction> transactions) {
        this.accountId = checkNotNull(accountId, "Null BankAccount accountId not permitted");
        this.transactions = checkNotNull(transactions, "Null BankAccount transactions not permitted");
        cachedBalance = Optional.absent();
    }

    public String getAccountId() {
        return accountId;
    }

    public ImmutableList<Transaction> getTransactions() {
        return transactions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String accountId;
        private ImmutableList<Transaction> transactions;

        private Builder() {
        }

        public Builder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder withTransactions(ImmutableList<Transaction> transactions) {
            for (Transaction transaction : transactions) {
                checkState(accountId.equals(transaction.getDestinationAccountCode()),
                        "Invalid transaction [%s] doesn't belong to this account [%s]",
                        transaction, accountId);
            }
            this.transactions = transactions;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(checkNotNull(accountId), transactions);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, transactions);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId='" + accountId + '\'' +
                ", transactions=" + transactions +
                '}';
    }

    public Long getBalance() {
        if (!cachedBalance.isPresent()) {
            cachedBalance = Optional.of(sumTotal());
        }
        return cachedBalance.get();  // Long is immutable, safe to share reference
    }

    private long sumTotal() {
        long total = 0;
        for (Transaction transaction : getTransactions()) {
            total += transaction.getAmount();
        }
        return total;
    }
}
