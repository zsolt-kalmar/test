package com.zsolt.jpmc.bookstore;

public interface Transaction {
    
    public String process(Inventory inventory, String... params) throws BadParameterException;
    
    public TransactionType getType();
}