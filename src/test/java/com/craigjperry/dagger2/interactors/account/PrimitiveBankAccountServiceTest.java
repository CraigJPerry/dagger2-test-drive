package com.craigjperry.dagger2.interactors.account;

import com.craigjperry.dagger2.interactors.account.error.BankAccountNotFoundException;
import com.craigjperry.dagger2.interactors.account.validator.AccountTransactionValidator;
import com.craigjperry.dagger2.interfaceexternal.datastorage.FakeInMemoryBankAccountRepository;
import org.junit.Before;
import org.junit.Test;

import static com.craigjperry.dagger2.interactors.account.validator.FakeAccountTransactionValidationEngine.alwaysPass;
import static org.assertj.core.api.Assertions.assertThat;

public class PrimitiveBankAccountServiceTest {

    private BankAccountRepository bankAccountRepository;
    private PrimitiveBankAccountService bankAccountService;

    @Before
    public void setUp() throws Exception {
        bankAccountRepository = new FakeInMemoryBankAccountRepository();
        AccountTransactionValidator validationEngine = alwaysPass();
        bankAccountService = new PrimitiveBankAccountService(bankAccountRepository, validationEngine);
    }

    @Test
    public void accountsCanBeRetrievedById() throws Exception {
        assertThat(bankAccountService.getAccount("1234"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("accountId", "1234");
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
