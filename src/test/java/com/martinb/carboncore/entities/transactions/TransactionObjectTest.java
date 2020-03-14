package com.martinb.carboncore.entities.transactions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionObjectTest {

    @Test
    public void checkObjectIdAndObjectValue() {

        TransactionObject<String> txObject = new TransactionObject<String>("test");

        assertEquals("test", txObject.getObject());
        assertThat(txObject.getObjectId(), matchesPattern("tx-obj-[0-9]+-[0-9]+"));
    }

    @Test
    public void checkToStringTest() {
        TransactionObject<String> txObject = new TransactionObject<String>("test");
        TransactionObject<String> mockedTxObject = Mockito.spy(txObject);

        when(mockedTxObject.getObjectId()).thenReturn("tx-obj-0000-00001111");

        assertEquals("[tx-obj-0000-00001111]: test", mockedTxObject.toString());
    }
}
