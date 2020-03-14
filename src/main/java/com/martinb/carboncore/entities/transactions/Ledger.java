package com.martinb.carboncore.entities.transactions;

import java.util.Date;
import java.util.HashMap;

public class Ledger {

    private final HashMap<Date, Transaction> transactionMap;

    public Ledger() {
        transactionMap = new HashMap<Date, Transaction>();
    }

    public HashMap<Date, Transaction> getTransactionMap() {
        return transactionMap;
    }

    public void addTransaction(Transaction transaction) {
        Date date = new Date();
        transactionMap.put(date, transaction);
    }

    public void addTransaction(Date date, Transaction transaction) {
        transactionMap.put(date, transaction);
    }
}
