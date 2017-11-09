package com.zsolt.jpmc.bookstore;

public enum TransactionType {
    UNKNOWN, BUY, MULTIBUY, RETURN, MULTIRETURN, QUERY, RECEIVE, UPDATE;
    
    public static TransactionType fromString(String type) {
        try {
            return TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return UNKNOWN;
        }
    }
}
