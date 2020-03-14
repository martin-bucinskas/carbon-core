package com.martinb.carboncore.entities.transactions;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicTransactionTest {

    @Test
    public void generateTransactionIdTest() {

        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};

        assertThat(basicTransaction.getTransactionId(), matchesPattern("tx-[0-9]+-[0-9]+"));
    }

    @Test
    public void stateTest() {
        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};

        assertEquals(TransactionState.DRAFT, basicTransaction.state());
    }

    @Test
    public void abortTest() {
        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};

        assertFalse(basicTransaction.abort());
    }

    @Test
    public void commitChangesToCommittedWithNewTransactionTest() throws Exception {
        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};

        String transactionId = basicTransaction.getTransactionId();

        assertEquals(transactionId, basicTransaction.commit());
        assertEquals(TransactionState.COMMITTED, basicTransaction.state());
    }

    @Test
    public void commitThrowsAnExceptionAfterCommittingMultipleTimesTest() throws Exception {
        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};

        String transactionId = basicTransaction.getTransactionId();

        assertEquals(transactionId, basicTransaction.commit());
        assertEquals(TransactionState.COMMITTED, basicTransaction.state());
        assertThrows(Exception.class, basicTransaction::commit);
    }

    @Test
    public void commitThrowsExceptionOnIllegalStates() {
        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};

        List<TransactionState> illegalStates = Arrays.asList(
            TransactionState.COMMITTING,
            TransactionState.COMMITTED,
            TransactionState.FAILED
        );

        for (TransactionState transactionState : illegalStates) {
            basicTransaction.setTransactionState(transactionState);
            assertThrows(Exception.class, basicTransaction::commit);
        }
    }

    @Test
    public void addObjectToTransaction() {
        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};
        TransactionObject<String> transactionObject = new TransactionObject<>("test");

        assertEquals(0, basicTransaction.getTransactionObjects().size());

        basicTransaction.addToTransaction(transactionObject);

        assertEquals(1, basicTransaction.getTransactionObjects().size());
    }
}
