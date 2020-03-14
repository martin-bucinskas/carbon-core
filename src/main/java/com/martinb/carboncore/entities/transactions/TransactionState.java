package com.martinb.carboncore.entities.transactions;

public enum TransactionState {

    DRAFT("draft"),
    COMMITTING("committing"),
    COMMITTED("committed"),
    FAILED("failed"),
    UNKNOWN("unknown");

    private final String id;

    TransactionState(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
