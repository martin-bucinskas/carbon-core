package com.martinb.carboncore.entities.transactions;

import com.martinb.carboncore.utils.DateTimeImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LedgerTest {

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
    public void storingAndRetrievingTransactionTest() {
        BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        Ledger ledger = new Ledger();

        String transactionId = basicTransaction.getTransactionId();

        ledger.addTransaction(transactionId, basicTransaction);

        assertEquals(1, ledger.getTransactionMap().size());

        Transaction<?> retrievedTransaction = ledger.getTransactionMap().get(transactionId);
        assertEquals(transactionId, retrievedTransaction.getTransactionId());
        assertEquals(basicTransaction, retrievedTransaction);
    }

    @Test
    public void storeAndRetrieveTransactionNoIdTest() {
        BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        Ledger ledger = new Ledger();

        ledger.addTransaction(basicTransaction);
        assertEquals(1, ledger.getTransactionMap().size());

        Transaction<?> retrievedTransaction = ledger.getTransactionMap().get(basicTransaction.getTransactionId());

        assertEquals(basicTransaction, retrievedTransaction);
    }

    @Test
    public void removeTransactionFromLedgerTest() {
        BasicTransaction<String> basicTransaction = getBasicTransactionInstance();
        Ledger ledger = new Ledger();

        ledger.addTransaction(basicTransaction);
        assertEquals(1, ledger.getTransactionMap().size());

        Transaction<?> retrievedTransaction = ledger.getTransactionMap().get(basicTransaction.getTransactionId());

        assertEquals(basicTransaction, retrievedTransaction);

        ledger.removeTransaction(basicTransaction.getTransactionId());

        assertEquals(0, ledger.getTransactionMap().size());
    }
}
