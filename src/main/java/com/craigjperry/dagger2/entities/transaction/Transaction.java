package com.craigjperry.dagger2.entities.transaction;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Transaction {

    private final Long amount;
    private final String destinationAccountCode;

    // $Deity bless Intellij, everything south of here is auto generated.
    // If you add a new field, just delete the below & regen it all

    private Transaction(Long amount, String destinationAccountCode) {
        this.amount = checkNotNull(amount, "amount");
        this.destinationAccountCode = checkNotNull(destinationAccountCode, "destinationAccountCode");
    }

    public Long getAmount() {
        return amount;
    }

    public String getDestinationAccountCode() {
        return destinationAccountCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long amount;
        private String destinationAccountCode;

        private Builder() {
        }

        public Builder withAmount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder withDestinationAccountCode(String destinationAccountCode) {
            this.destinationAccountCode = destinationAccountCode;
            return this;
        }

        public Transaction build() {
            return new Transaction(amount, destinationAccountCode);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(destinationAccountCode, that.destinationAccountCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, destinationAccountCode);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", destinationAccountCode='" + destinationAccountCode + '\'' +
                '}';
    }
}
