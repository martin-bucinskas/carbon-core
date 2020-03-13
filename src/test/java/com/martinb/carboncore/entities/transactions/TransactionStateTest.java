package com.martinb.carboncore.entities.transactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionStateTest {

    @Test
    public void hasCorrectValues() {

        assertEquals("draft", TransactionStatus.DRAFT.toString());
        assertEquals("committing", TransactionStatus.COMMITTING.toString());
        assertEquals("committed", TransactionStatus.COMMITTED.toString());
        assertEquals("failed", TransactionStatus.FAILED.toString());
        assertEquals("unknown", TransactionStatus.UNKNOWN.toString());
    }

    @Test
    public void sanityCheck() {

        assertEquals(5, TransactionStatus.values().length);
    }
}
