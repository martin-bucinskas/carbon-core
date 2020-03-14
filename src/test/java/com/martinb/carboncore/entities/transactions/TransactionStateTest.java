package com.martinb.carboncore.entities.transactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionStateTest {

    @Test
    public void hasCorrectValues() {

        assertEquals("draft", TransactionState.DRAFT.toString());
        assertEquals("committing", TransactionState.COMMITTING.toString());
        assertEquals("committed", TransactionState.COMMITTED.toString());
        assertEquals("failed", TransactionState.FAILED.toString());
        assertEquals("unknown", TransactionState.UNKNOWN.toString());
    }

    @Test
    public void sanityCheck() {

        assertEquals(5, TransactionState.values().length);
    }
}
