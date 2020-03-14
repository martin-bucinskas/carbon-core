package com.martinb.carboncore.entities.transactions;

public interface Transaction<T> {

    void setTransactionState(TransactionState transactionState);

    String commit() throws Exception;

    boolean abort();

    TransactionState state();

    String generateTransactionId();

    String getTransactionId();
}
