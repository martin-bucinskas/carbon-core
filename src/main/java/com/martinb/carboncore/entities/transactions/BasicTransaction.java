package com.martinb.carboncore.entities.transactions;

import java.util.LinkedList;
import java.util.Random;

public abstract class BasicTransaction implements Transaction {

    private String transactionId;
    private TransactionState transactionState;
    private LinkedList<TransactionObject<?>> transactionObjects;

    public BasicTransaction() {
        transactionId = generateTransactionId();
        transactionState = TransactionState.DRAFT;
        transactionObjects = new LinkedList<TransactionObject<?>>();
    }

    public void addToTransaction(TransactionObject<?> transactionObject) {
        transactionObjects.add(transactionObject);
    }

    public LinkedList<TransactionObject<?>> getTransactionObjects() {
        return transactionObjects;
    }

    @Override
    public String commit() throws Exception {
        if (transactionState == TransactionState.COMMITTING
            || transactionState == TransactionState.COMMITTED
            || transactionState == TransactionState.FAILED) {
            throw new Exception("The transaction `" + transactionId + "` is currently in the state: `" + transactionState.toString().toUpperCase() + "`");
        }

        transactionState = TransactionState.COMMITTING;

        // Insert business logic here.

        transactionState = TransactionState.COMMITTED;

        return transactionId;
    }

    @Override
    public boolean abort() {
        return false;
    }

    @Override
    public TransactionState state() {
        return transactionState;
    }

    @Override
    public String generateTransactionId() {
        Random random = new Random();
        long randomLong = random.nextLong();
        int randomInt = random.nextInt(65565);
        return "tx-" + randomInt + "-" + randomLong;
    }

    @Override
    public String toString() {
        return transactionId;
    }
}
