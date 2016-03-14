package com.craigjperry.dagger2.interactors.account;

import com.craigjperry.dagger2.entities.account.BankAccount;
import com.craigjperry.dagger2.interactors.account.error.BankAccountNotFoundException;
import com.craigjperry.dagger2.interfaceexternal.application.DaggerTestBankComponent;
import com.craigjperry.dagger2.interfaceexternal.application.TestBankComponent;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountServiceIT {

    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;

    public BankAccountServiceIT() {
        // TODO: We need a @RunWith runner for dagger!
        TestBankComponent testBankComponent = DaggerTestBankComponent.create();
        this.bankAccountRepository = testBankComponent.makeTestBankAccountRepository();
        this.bankAccountService = testBankComponent.makeTestBankAccountService();
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

        assertThat(bankAccountRepository.find(bankAccount.getAccountId()).get())
                .isEqualTo(bankAccount);
    }

    @Test(expected = BankAccountNotFoundException.class)
    public void absentAccountRaisesException() throws Exception {
        bankAccountService.getAccount("1");
    }

    @Test(expected = NullPointerException.class)
    public void nullAccountIdLookupsAreRejected() throws Exception {
        bankAccountService.getAccount(null);
    }
}
