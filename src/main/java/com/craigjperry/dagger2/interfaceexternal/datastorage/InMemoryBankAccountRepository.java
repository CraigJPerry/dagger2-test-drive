package com.craigjperry.dagger2.interfaceexternal.datastorage;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.BankAccountRepository;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.collect.Maps.newHashMap;

class InMemoryBankAccountRepository implements BankAccountRepository {
    private final AtomicLong accountIdFountain;
    private final Map<String, BankAccount> bankAccountStore;

    InMemoryBankAccountRepository() {
        bankAccountStore = newHashMap();
        accountIdFountain = new AtomicLong();
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
