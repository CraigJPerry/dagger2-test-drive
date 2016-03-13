package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.datastorage.Repository;
import com.craigjperry.dagger2.entities.Transaction;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.collect.Maps.newHashMap;

final class InMemoryBankAccountRepository implements Repository<String, BankAccount> {
    private AtomicLong accountIdFountain = new AtomicLong();
    private Map<String, BankAccount> bankAccountStore;

    public InMemoryBankAccountRepository() {
        this.bankAccountStore = newHashMap();
    }

    @Override
    public BankAccount add() {
        BankAccount account = BankAccount.builder()
                .withAccountId(String.valueOf(accountIdFountain.incrementAndGet()))
                .withTransactions(ImmutableList.<Transaction>of())
                .build();
        bankAccountStore.put(account.getAccountId(), account);
        return account;
    }

    @Override
    public Optional<BankAccount> find(String id) {
        return Optional.fromNullable(bankAccountStore.get(id));
    }

    @Override
    public Collection<BankAccount> findAll() {
        return bankAccountStore.values();
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        return bankAccountStore.put(bankAccount.getAccountId(), bankAccount);
    }

    @Override
    public BankAccount delete(String id) {
        return bankAccountStore.remove(id);
    }
}
