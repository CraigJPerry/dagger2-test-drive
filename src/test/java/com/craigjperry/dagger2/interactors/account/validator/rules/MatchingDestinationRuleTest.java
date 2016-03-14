package com.craigjperry.dagger2.interactors.account.validator.rules;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.interactors.account.validator.rules.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import static com.craigjperry.dagger2.interactors.account.validator.rules.MatchingDestinationRule.matchingDestinationRule;

public class MatchingDestinationRuleTest {

    private MatchingDestinationRule matchingDestinationRule;

    @Before
    public void setUp() throws Exception {
        matchingDestinationRule = matchingDestinationRule();
    }

    @Test()
    public void permitsMatchingDestinationTransactions() throws Exception {
        matchingDestinationRule.validate(BankAccount.builder()
                .withAccountId("1")
                .withTransactions(ImmutableList.<Transaction>of())
                .build(), transactionFixtureOf(1L));
    }

    @Test(expected = BankAccountTransactionValidationException.class)
    public void deniesNonMatchingDestinationTransactions() throws Exception {
        matchingDestinationRule.validate(BankAccount.builder()
                .withAccountId("2")
                .withTransactions(ImmutableList.<Transaction>of())
                .build(), transactionFixtureOf(1L));
    }

    private Transaction transactionFixtureOf(Long amount) {
        return Transaction.builder()
                .withDestinationAccountCode("1")
                .withAmount(amount)
                .build();
    }

    private ImmutableList<Transaction> transactionListFixtureOf(Transaction... transactions) {
        ImmutableList.Builder<Transaction> tBuilder = ImmutableList.builder();
        for (Transaction t : transactions) {
            tBuilder.add(t);
        }
        return tBuilder.build();
    }
}
