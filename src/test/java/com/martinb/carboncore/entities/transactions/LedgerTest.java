package com.martinb.carboncore.entities.transactions;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LedgerTest {

    @Test
    public void storingAndRetrievingTransactionTest() {
        BasicTransaction<String> basicTransaction = new BasicTransaction<String>() {};
        Ledger ledger = new Ledger();

        String transactionId = basicTransaction.getTransactionId();
        Date testDate = new Date();

        ledger.addTransaction(testDate, basicTransaction);

        assertEquals(1, ledger.getTransactionMap().size());

        Transaction<?> retrievedTransaction = ledger.getTransactionMap().get(testDate);
        assertEquals(transactionId, retrievedTransaction.getTransactionId());
    }
}
