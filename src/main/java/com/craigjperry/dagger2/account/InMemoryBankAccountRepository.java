package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.datastorage.Repository;
import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.collect.Maps.newHashMap;

final class InMemoryBankAccountRepository implements Repository<Long, BankAccount> {
    private AtomicLong accountIdFountain = new AtomicLong();
    private Map<Long, BankAccount> bankAccountStore;

    public InMemoryBankAccountRepository() {
        this.bankAccountStore = newHashMap();
    }

    @Override
    public BankAccount add() {
        BankAccount account = BankAccount.create(accountIdFountain.incrementAndGet());
        bankAccountStore.put(account.accountId(), account);
        return account;
    }

    @Override
    public Optional<BankAccount> find(Long id) {
        return Optional.fromNullable(bankAccountStore.get(id));
    }

    @Override
    public Collection<BankAccount> findAll() {
        return bankAccountStore.values();
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        return bankAccountStore.put(bankAccount.accountId(), bankAccount);
    }

    @Override
    public BankAccount delete(Long id) {
        return bankAccountStore.remove(id);
    }
}
