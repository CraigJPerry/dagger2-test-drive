package com.craigjperry.dagger2.account.validator;

import com.craigjperry.dagger2.account.BankAccount;
import com.craigjperry.dagger2.account.validator.error.BankAccountTransactionValidationException;
import com.craigjperry.dagger2.account.validator.rules.AccountTransactionValidationRule;
import com.craigjperry.dagger2.entities.Transaction;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;

public class AccountTransactionValidationEngine {
    private final List<AccountTransactionValidationRule> rules;

    private AccountTransactionValidationEngine(List<AccountTransactionValidationRule> rules) {
        this.rules = rules;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<AccountTransactionValidationRule> rules = newArrayList();

        private Builder() {
        }

        public Builder withRule(AccountTransactionValidationRule rule) {
            rules.add(rule);
            return this;
        }

        public AccountTransactionValidationEngine build() {
            checkState(rules.size() > 0, "No validation rules configured");
            return new AccountTransactionValidationEngine(rules);
        }
    }

    public void validate(BankAccount a, Transaction t) throws BankAccountTransactionValidationException {
        for (AccountTransactionValidationRule rule : this.rules) {
            rule.validate(a, t);
        }
    }
}
