package com.craigjperry.dagger2.interfaceexternal.datastorage;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class FakeInMemoryBankAccountRepository extends InMemoryBankAccountRepository {

    public final BankAccount BANK_ACCOUNT1;
    public final BankAccount BANK_ACCOUNT2;

    public FakeInMemoryBankAccountRepository() {
        BANK_ACCOUNT1 = makeAccount("1234", 10L, -7L, 4L);
        BANK_ACCOUNT2 = makeAccount("4321", 12L, 34L, 56L);
    }

    @Override
    public BankAccount add() {
        return BANK_ACCOUNT1;
    }

    @Override
    public Optional<BankAccount> find(String id) {
        if (BANK_ACCOUNT1.getAccountId().equals(id)) {
            return Optional.of(BANK_ACCOUNT1);
        }
        return Optional.absent();
    }

    @Override
    public Collection<BankAccount> findAll() {
        return newArrayList(BANK_ACCOUNT1, BANK_ACCOUNT2);
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        return BANK_ACCOUNT1;
    }

    @Override
    public BankAccount delete(String id) {
        return BANK_ACCOUNT1;
    }

    private BankAccount makeAccount(String destinationAccountCode, Long... amounts) {
        return BankAccount.builder()
                .withAccountId(destinationAccountCode)
                .withTransactions(makeTransactions(destinationAccountCode, amounts))
                .build();
    }

    private ImmutableList<Transaction> makeTransactions(String destinationAccountCode, Long... amounts) {
        ImmutableList.Builder<Transaction> builder = ImmutableList.builder();
        for (Long amount : amounts) {
            builder.add(Transaction.builder()
                    .withAmount(amount)
                    .withDestinationAccountCode(destinationAccountCode)
                    .build());
        }
        return builder.build();
    }
}
