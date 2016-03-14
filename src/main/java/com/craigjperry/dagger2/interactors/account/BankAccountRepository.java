package com.craigjperry.dagger2.interactors.account;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.datastorage.Repository;

public interface BankAccountRepository extends Repository<String, BankAccount> {
}
