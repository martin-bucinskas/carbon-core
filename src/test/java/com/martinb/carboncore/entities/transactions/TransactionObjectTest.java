package com.martinb.carboncore.entities.transactions;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionObjectTest {

    @Test
    public void checkObjectIdAndObjectValue() {

        TransactionObject<String> txObject = new TransactionObject<String>("test");

        assertEquals("test", txObject.getObject());
        assertThat(txObject.getObjectId(), matchesPattern("tx-obj-[0-9]+-[0-9]+"));
    }
}
