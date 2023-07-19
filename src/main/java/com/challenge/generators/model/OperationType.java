package com.challenge.generators.model;

public enum OperationType {
    SUM("sum"),
    AVERAGE("average"),
    MIN("min"),
    MAX("max");

    private final String operation;

    OperationType(String operation) {
        this.operation = operation;
    }

    public String getOperation(){
        return  operation;
    }

    public static OperationType fromString(String value) {
        for (OperationType item : OperationType.values()) {
            if (item.operation.equalsIgnoreCase(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid operation value: " + value);
    }
}
