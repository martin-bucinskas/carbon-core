package com.martinb.carboncore.entities.transactions;

public interface Transaction {

    String commit();

    boolean abort();

    TransactionStatus status();
}
