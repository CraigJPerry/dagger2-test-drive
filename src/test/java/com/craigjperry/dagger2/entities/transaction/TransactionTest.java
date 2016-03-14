package com.craigjperry.dagger2.entities.transaction;

import org.junit.Test;

public class TransactionTest {

    @Test (expected = NullPointerException.class)
    public void amountIsNotNullable() throws Exception {
        Transaction.builder()
                .withAmount(null)
                .withDestinationAccountCode("1")
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void destinationAccountCodeIsNotNullable() throws Exception {
        Transaction.builder()
                .withDestinationAccountCode(null)
                .withAmount(1L)
                .build();
    }
}
