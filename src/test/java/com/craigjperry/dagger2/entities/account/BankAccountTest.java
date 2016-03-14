package com.craigjperry.dagger2.entities.account;

import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {

    @Test
    public void bankAccountsHaveAZeroOpeningBalance() throws Exception {
        BankAccount bankAccount = BankAccount.builder()
                .withAccountId("1234")
                .withTransactions(ImmutableList.<Transaction>of())
                .build();
        assertThat(bankAccount.getBalance()).isEqualTo(0L);
    }

    @Test
    public void transactionsAreCumulative() throws Exception {
        ImmutableList<Transaction> transactions = ImmutableList.of(
                Transaction.builder()
                        .withDestinationAccountCode("1234")
                        .withAmount(10L)
                        .build(),
                Transaction.builder()
                        .withDestinationAccountCode("1234")
                        .withAmount(-7L)
                        .build()
        );
        BankAccount account = BankAccount.builder()
                .withAccountId("1234")
                .withTransactions(transactions)
                .build();

        assertThat(account.getBalance()).isEqualTo(3);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotContainAlienTransactions() throws Exception {
        ImmutableList<Transaction> transactions = ImmutableList.of(
                Transaction.builder().withDestinationAccountCode("1234").withAmount(10L).build()
        );
        BankAccount.builder()
                .withAccountId("9999")
                .withTransactions(transactions);
    }
}
