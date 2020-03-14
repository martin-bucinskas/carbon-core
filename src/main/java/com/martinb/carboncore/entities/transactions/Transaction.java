package com.martinb.carboncore.entities.transactions;

public interface Transaction {

    String commit() throws Exception;

    boolean abort();

    TransactionState state();

    String generateTransactionId();
}
