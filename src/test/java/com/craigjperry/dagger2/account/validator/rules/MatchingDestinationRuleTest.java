package com.craigjperry.dagger2.account.validator.rules;

import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.Transaction;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.craigjperry.dagger2.account.BankAccountTest.bankAccountFixture;
import static com.craigjperry.dagger2.account.BankAccountTest.emptyBankAccountFixture;
import static com.craigjperry.dagger2.account.validator.rules.MatchingDestinationRule.matchingDestinationRule;

public class MatchingDestinationRuleTest {

    private MatchingDestinationRule matchingDestinationRule;

    @Before
    public void setUp() throws Exception {
        matchingDestinationRule = matchingDestinationRule();
    }

    @Test()
    public void permitsMatchingDestinationTransactions() throws Exception {
        matchingDestinationRule.validate(emptyBankAccountFixture(), transactionFixtureOf(1L));
    }

    @Test(expected = BankAccountTransactionValidationException.class)
    public void deniesNonMatchingDestinationTransactions() throws Exception {
        matchingDestinationRule.validate(bankAccountFixture(2L, ImmutableList.<Transaction>of()), transactionFixtureOf(1L));
    }

    private ImmutableList<Transaction> existingTransactionsFixtureOf(Collection<Transaction> t) {
        return ImmutableList.<Transaction>builder().addAll(t).build();
    }

    private Transaction transactionFixtureOf(Long amount) {
        return Transaction.builder().withDestinationAccountCode("1").withAmount(amount).build();
    }
}
