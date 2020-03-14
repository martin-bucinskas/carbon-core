package com.martinb.carboncore.entities.transactions;

import java.util.Date;
import java.util.HashMap;

public class Ledger {

    private final HashMap<String, Transaction<?>> transactionMap;

    public Ledger() {
        transactionMap = new HashMap<String, Transaction<?>>();
    }

    public HashMap<String, Transaction<?>> getTransactionMap() {
        return transactionMap;
    }

    public void addTransaction(Transaction<?> transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);
    }

    public void addTransaction(String transactionId, Transaction<?> transaction) {
        transactionMap.put(transactionId, transaction);
    }

    public void removeTransaction(String transactionId) {
        transactionMap.remove(transactionId);
    }
}
