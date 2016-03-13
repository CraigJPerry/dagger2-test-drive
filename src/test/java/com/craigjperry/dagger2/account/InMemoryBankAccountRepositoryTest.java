package com.craigjperry.dagger2.account;

import com.craigjperry.dagger2.entities.Transaction;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryBankAccountRepositoryTest {

    private InMemoryBankAccountRepository bankAccountRepository;

    @Before
    public void setUp() throws Exception {
        bankAccountRepository = new InMemoryBankAccountRepository();
    }

    @Test
    public void bankAccountsAreSavedOnCreation() throws Exception {
        BankAccount account = bankAccountRepository.add();

        assertThat(bankAccountRepository.find(account.getAccountId()).get())
                .isEqualTo(account);
    }

    @Test
    public void nonExistantAccountsReturnAnAbsentOptional() throws Exception {
        assertThat(bankAccountRepository.find("1"))
                .isEqualTo(Optional.<BankAccount>absent());
    }

    @Test
    public void findReturnsTheLatestVersionOfAnUpdatedAccount() throws Exception {
        BankAccount v1 = bankAccountRepository.add();
        Transaction t = Transaction.builder().withDestinationAccountCode(v1.getAccountId()).withAmount(100L).build();
        ImmutableList<Transaction> newTransactions = ImmutableList.<Transaction>builder()
                .addAll(v1.getTransactions()).add(t).build();
        BankAccount v2 = BankAccount.builder()
                .withAccountId(v1.getAccountId())
                .withTransactions(newTransactions).build();

        bankAccountRepository.update(v2);

        assertThat(bankAccountRepository.find(v1.getAccountId()).get())
                .isEqualTo(v2).isNotEqualTo(v1);
    }

    @Test
    public void deleteEliminatesAnAccountFromTheFindAllResults() throws Exception {
        BankAccount a = bankAccountRepository.add();
        bankAccountRepository.add();
        bankAccountRepository.add();

        bankAccountRepository.delete(a.getAccountId());

        assertThat(bankAccountRepository.findAll())
                .doesNotContain(a);
    }
}
