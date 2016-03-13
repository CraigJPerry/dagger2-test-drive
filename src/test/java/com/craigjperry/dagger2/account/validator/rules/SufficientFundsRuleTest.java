package com.craigjperry.dagger2.account.validator.rules;

import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.Transaction;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.craigjperry.dagger2.account.BankAccountTest.bankAccountFixture;
import static com.craigjperry.dagger2.account.BankAccountTest.emptyBankAccountFixture;
import static com.craigjperry.dagger2.account.validator.rules.SufficientFundsRule.sufficientFundsRule;

public class SufficientFundsRuleTest {

    private SufficientFundsRule sufficientFundsRule;

    @Before
    public void setUp() throws Exception {
        sufficientFundsRule = sufficientFundsRule();
    }

    @Test
    public void alwaysPermitsTransactionsWithPositiveValue() throws Exception {
        sufficientFundsRule.validate(emptyBankAccountFixture(), transactionFixtureOf(1L));
    }

    @Test(expected = BankAccountTransactionValidationException.class)
    public void deniesTransactionsWhichResultInNegativeBalance() throws Exception {
        sufficientFundsRule.validate(emptyBankAccountFixture(), transactionFixtureOf(-1L));
    }

    @Test()
    public void permitsNegativeTransactionsWhichResultInPositiveBalance() throws Exception {
        ImmutableList<Transaction> existingTransactions = existingTransactionsFixtureOf(ImmutableList.of(transactionFixtureOf(10L)));
        sufficientFundsRule.validate(bankAccountFixture(1L, existingTransactions), transactionFixtureOf(-9L));
    }

    private ImmutableList<Transaction> existingTransactionsFixtureOf(Collection<Transaction> t) {
        return ImmutableList.<Transaction>builder().addAll(t).build();
    }

    private Transaction transactionFixtureOf(Long amount) {
        return Transaction.builder().withDestinationAccountCode("1").withAmount(amount).build();
    }
}
