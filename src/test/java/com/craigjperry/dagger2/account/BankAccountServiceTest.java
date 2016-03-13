package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.account.error.BankAccountNotAvailableException;
import com.craigjperry.dagger2.account.validator.AccountTransactionValidationEngine;
import org.junit.Before;
import org.junit.Test;

import static com.craigjperry.dagger2.account.validator.rules.AlwaysPassFakeRule.alwaysPassFakeRule;
import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountServiceTest {

    private InMemoryBankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;

    @Before
    public void setUp() throws Exception {
        bankAccountRepository = new InMemoryBankAccountRepository();
        AccountTransactionValidationEngine validationEngine = AccountTransactionValidationEngine.builder().withRule(alwaysPassFakeRule()).build();
        bankAccountService = new BankAccountService(bankAccountRepository, validationEngine);
    }

    @Test
    public void newAccountsArePersistedImmediately() throws Exception {
        BankAccount bankAccount = bankAccountService.openNewAccount();

        assertThat(bankAccountRepository.findAll())
                .containsExactly(bankAccount);
    }

    @Test
    public void accountsCanBeRetrievedById() throws Exception {
        BankAccount bankAccount = bankAccountService.openNewAccount();

        assertThat(bankAccountRepository.find(bankAccount.accountId()).get())
                .isEqualTo(bankAccount);
    }

    @Test(expected = BankAccountNotAvailableException.class)
    public void absentAccountRaisesException() throws Exception {
        bankAccountService.getAccount(1L);
    }

    @Test(expected = NullPointerException.class)
    public void nullAccountIdLookupsAreRejected() throws Exception {
        bankAccountService.getAccount(null);
    }


}
