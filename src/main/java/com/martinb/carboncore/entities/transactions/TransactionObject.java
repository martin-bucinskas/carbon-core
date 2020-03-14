package com.martinb.carboncore.entities.transactions;

import java.util.Random;

public class TransactionObject<T> {

    private final String objectId;
    private final T object;

    public TransactionObject(T object) {
        this.object = object;
        this.objectId = generateObjectId();
    }

    public T getObject() {
        return object;
    }

    public String getObjectId() {
        return objectId;
    }

    private String generateObjectId() {
        Random random = new Random();
        long randomLong = random.nextLong();
        int randomInt = random.nextInt(65565);
        return "tx-" + randomInt + "-" + randomLong;
    }

    @Override
    public String toString() {
        return "[" + objectId + "]: " + object.toString();
    }
}
