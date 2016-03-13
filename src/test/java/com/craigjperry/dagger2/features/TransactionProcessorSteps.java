package com.craigjperry.dagger2.features;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.BankAccountService;
import com.craigjperry.dagger2.transaction.Transaction;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionProcessorSteps {

    @Inject
    BankAccountService bankAccountService;

    public TransactionProcessorSteps() {
        DaggerRunAcceptanceIT_TestingBankComponent.create().inject(this);
    }

    static class BankAccountRow {
        Long accountId;
        Long balance;
    }

    @Given("^bank accounts$")
    public void bankAccounts(List<BankAccountRow> rows) throws Throwable {
        for (BankAccountRow row : rows) {
            BankAccount bankAccount = bankAccountService.openNewAccount();
            bankAccountService.appendTransaction(
                    bankAccount.accountId(),
                    Transaction.builder()
                            .sourceAccount(1234L)
                            .destinationAccount(row.accountId)
                            .amount(row.balance)
                            .build()
            );
        }
    }

    static class TransactionRow {
        Long transactionId;
        Long account;
        Long amount;
    }

    @Given("^a queue of inbound transactions$")
    public void aQueueOfInboundTransactions(List<TransactionRow> rows) throws Throwable {
        for (TransactionRow row : rows) {
            bankAccountService.appendTransaction(
                    row.account,
                    Transaction.builder()
                            .sourceAccount(1234L)
                            .destinationAccount(row.account)
                            .amount(row.amount)
                            .build()
            );
        }
    }

    @When("^all transactions are processed$")
    public void allTransactionsAreProcessed() throws Throwable {
        // no-op
    }

    @And("^bank account balances are$")
    public void bankAccountBalancesAre(List<BankAccountRow> rows) throws Throwable {
        for (BankAccountRow row : rows) {
            BankAccount account = bankAccountService.getAccount(row.accountId);
            assertThat(account.getBalance())
                    .isEqualTo(row.balance);
        }
    }
}
