package com.martinb.carboncore.entities.transactions;

import com.martinb.carboncore.utils.DateTime;
import com.martinb.carboncore.utils.DateTimeImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasicTransactionTest {

    public BasicTransaction<String> getBasicTransactionInstance() {
        return new BasicTransaction<String>(new DateTimeImpl()) {
            @Override
            public void preCommit() {

            }

            @Override
            public void postCommit() {

            }
        };
    }

    @Test
    public void generateTransactionIdTest() {
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        assertThat(basicTransaction.getTransactionId(), matchesPattern("tx-[0-9]+-[0-9]+"));
    }

    @Test
    public void stateTest() {
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        assertEquals(TransactionState.DRAFT, basicTransaction.state());
    }

    @Test
    public void abortTest() {
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        assertFalse(basicTransaction.abort());
    }

    @Test
    public void commitChangesToCommittedWithNewTransactionTest() throws Exception {
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        final String transactionId = basicTransaction.getTransactionId();

        assertEquals(transactionId, basicTransaction.commit());
        assertEquals(TransactionState.COMMITTED, basicTransaction.state());
    }

    @Test
    public void commitThrowsAnExceptionAfterCommittingMultipleTimesTest() throws Exception {
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        final String transactionId = basicTransaction.getTransactionId();

        assertEquals(transactionId, basicTransaction.commit());
        assertEquals(TransactionState.COMMITTED, basicTransaction.state());
        assertThrows(Exception.class, basicTransaction::commit);
    }

    @Test
    public void commitThrowsExceptionOnIllegalStates() {
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();

        final List<TransactionState> illegalStates = Arrays.asList(
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
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        final TransactionObject<String> transactionObject = new TransactionObject<>("test");

        assertEquals(0, basicTransaction.getTransactionObjects().size());

        basicTransaction.addToTransaction(transactionObject);

        assertEquals(1, basicTransaction.getTransactionObjects().size());
    }

    @Test
    public void commitRunsPreCommitAndPostCommitTest() throws Exception {
        final BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        final BasicTransaction<String> mockedTransaction = Mockito.spy(basicTransaction);

        mockedTransaction.commit();

        verify(mockedTransaction, times(1)).preCommit();
        verify(mockedTransaction, times(1)).postCommit();
    }

    @Test
    public void getTransactionDateTest() {
        final Date date = new Date();

        final DateTime dateTime = Mockito.mock(DateTime.class);
        Mockito.when(dateTime.getDate()).thenReturn(date);

        final BasicTransaction<String> basicTransaction = new BasicTransaction<String>(dateTime) {
            @Override
            public void preCommit() {

            }

            @Override
            public void postCommit() {

            }
        };

        final Date transactionDate = basicTransaction.getTransactionDate().getDate();
        assertEquals(date, transactionDate);
    }
}
