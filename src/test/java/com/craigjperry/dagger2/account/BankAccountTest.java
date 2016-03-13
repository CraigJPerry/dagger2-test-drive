package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.transaction.Transaction;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {

    public static BankAccount emptyBankAccountFixture() {
        return BankAccount.create(1L);
    }

    public static BankAccount bankAccountFixture(Long accountId, ImmutableList<Transaction> transactions) {
        return BankAccount.createWithTransactions(accountId, transactions);
    }

    @Test
    public void bankAccountsHaveAZeroOpeningBalance() throws Exception {
        BankAccount bankAccount = BankAccount.create(1234L);
        assertThat(bankAccount.getBalance()).isEqualTo(0L);
    }

    @Test
    public void transactionsAreCumulative() throws Exception {
        ImmutableList<Transaction> transactions = ImmutableList.of(
                Transaction.builder().sourceAccount(4321L).destinationAccount(1234L).amount(10L).build(),
                Transaction.builder().sourceAccount(4321L).destinationAccount(1234L).amount(-7L).build()
        );
        BankAccount account = BankAccount.createWithTransactions(1234L, transactions);

        assertThat(account.getBalance()).isEqualTo(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeAccountIdsAreNotPermitted() throws Exception {
        BankAccount.create(-1L);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotContainAlienTransactions() throws Exception {
        ImmutableList<Transaction> transactions = ImmutableList.of(
                Transaction.builder().sourceAccount(4321L).destinationAccount(1234L).amount(10L).build()
        );
        BankAccount.createWithTransactions(9999L, transactions);
    }
}
