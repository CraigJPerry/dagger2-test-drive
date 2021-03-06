package com.craigjperry.dagger2.interactors.account.validator.rules;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.craigjperry.dagger2.interactors.account.validator.rules.SufficientFundsRule.sufficientFundsRule;

public class SufficientFundsRuleTest {

    private SufficientFundsRule sufficientFundsRule;

    @Before
    public void setUp() throws Exception {
        sufficientFundsRule = sufficientFundsRule();
    }

    @Test
    public void alwaysPermitsTransactionsWithPositiveValue() throws Exception {
        sufficientFundsRule.validate(BankAccount.builder()
                .withAccountId("1")
                .withTransactions(ImmutableList.<Transaction>of())
                .build(), transactionFixtureOf(1L));
    }

    @Test(expected = BankAccountTransactionValidationException.class)
    public void deniesTransactionsWhichResultInNegativeBalance() throws Exception {
        sufficientFundsRule.validate(BankAccount.builder()
                .withAccountId("1")
                .withTransactions(ImmutableList.<Transaction>of())
                .build(), transactionFixtureOf(-1L));
    }

    @Test()
    public void permitsNegativeTransactionsWhichResultInPositiveBalance() throws Exception {
        ImmutableList<Transaction> existingTransactions = existingTransactionsFixtureOf(ImmutableList.of(transactionFixtureOf(10L)));
        sufficientFundsRule.validate(BankAccount.builder()
                .withAccountId("1")
                .withTransactions(existingTransactions)
                .build(), transactionFixtureOf(-9L));
    }

    private ImmutableList<Transaction> existingTransactionsFixtureOf(Collection<Transaction> t) {
        return ImmutableList.<Transaction>builder().addAll(t).build();
    }

    private Transaction transactionFixtureOf(Long amount) {
        return Transaction.builder().withDestinationAccountCode("1").withAmount(amount).build();
    }
}
