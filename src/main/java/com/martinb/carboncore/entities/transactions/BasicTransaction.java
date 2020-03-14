package com.martinb.carboncore.entities.transactions;

import java.util.*;

public abstract class BasicTransaction<T> implements Transaction {

    private String transactionId;
    private TransactionState transactionState;
    private LinkedList<TransactionObject<T>> transactionObjects;

    private List<TransactionState> illegalStates = Arrays.asList(
        TransactionState.COMMITTING,
        TransactionState.COMMITTED,
        TransactionState.FAILED
    );

    public BasicTransaction() {
        transactionId = generateTransactionId();
        transactionState = TransactionState.DRAFT;
        transactionObjects = new LinkedList<TransactionObject<T>>();
    }

    public void addToTransaction(TransactionObject<T> transactionObject) {
        transactionObjects.add(transactionObject);
    }

    @Override
    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public LinkedList<TransactionObject<T>> getTransactionObjects() {
        return transactionObjects;
    }

    @Override
    public String commit() throws Exception {
        if (illegalStates.contains(transactionState)) {
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
        long randomLong = Math.abs(random.nextLong());
        int randomInt = Math.abs(random.nextInt(65565));
        return "tx-" + randomInt + "-" + randomLong;
    }

    @Override
    public String toString() {
        return transactionId;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }
}
