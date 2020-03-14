package com.martinb.carboncore.entities.transactions;

import com.martinb.carboncore.utils.DateTime;
import com.martinb.carboncore.utils.DateTimeImpl;

import java.util.*;

public abstract class BasicTransaction<T> implements Transaction<T> {

    private final String transactionId;
    private final DateTime transactionDate;

    private TransactionState transactionState;
    private LinkedList<TransactionObject<T>> transactionObjects;

    private List<TransactionState> illegalStates = Arrays.asList(
        TransactionState.COMMITTING,
        TransactionState.COMMITTED,
        TransactionState.FAILED
    );

    public BasicTransaction(final DateTime dateTime) {
        transactionId = generateTransactionId();
        transactionState = TransactionState.DRAFT;
        transactionObjects = new LinkedList<TransactionObject<T>>();
        transactionDate = dateTime;
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

    public abstract void preCommit();

    public abstract void postCommit();

    @Override
    public String commit() throws Exception {
        if (illegalStates.contains(transactionState)) {
            throw new Exception("The transaction `" + transactionId + "` is currently in the state: `" + transactionState.toString().toUpperCase() + "`");
        }

        transactionState = TransactionState.COMMITTING;

        preCommit();

        transactionState = TransactionState.COMMITTED;

        postCommit();

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
    public String getTransactionId() {
        return transactionId;
    }

    public DateTime getTransactionDate() {
        return transactionDate;
    }
}
