package com.martinb.carboncore.entities.transactions;

public enum TransactionStatus {

    DRAFT("draft"),
    COMMITTING("committing"),
    COMMITTED("committed"),
    FAILED("failed"),
    UNKNOWN("unknown");

    private final String id;

    TransactionStatus(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
