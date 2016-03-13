package com.craigjperry.dagger2.transaction;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Transaction {

    public static Builder builder() {
        return new AutoValue_Transaction.Builder();
    }

    public abstract Long amount();
    public abstract Long sourceAccount();
    public abstract Long destinationAccount();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder amount(Long amount);
        public abstract Builder sourceAccount(Long account);
        public abstract Builder destinationAccount(Long account);
        public abstract Transaction build();
    }
}
