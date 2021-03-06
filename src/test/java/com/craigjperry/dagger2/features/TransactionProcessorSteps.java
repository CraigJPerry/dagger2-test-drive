package com.craigjperry.dagger2.features;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.entities.transaction.Transaction;
import com.craigjperry.dagger2.interactors.account.BankAccountService;
import com.craigjperry.dagger2.interfaceexternal.application.DaggerTestBankComponent;
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
        bankAccountService = DaggerTestBankComponent.create().makeTestBankAccountService();
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
                    bankAccount.getAccountId(),
                    Transaction.builder()
                            .withDestinationAccountCode(row.accountId.toString())
                            .withAmount(row.balance)
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
                    row.account.toString(),
                    Transaction.builder()
                            .withDestinationAccountCode(row.account.toString())
                            .withAmount(row.amount)
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
            BankAccount account = bankAccountService.getAccount(row.accountId.toString());
            assertThat(account.getBalance())
                    .isEqualTo(row.balance);
        }
    }
}
