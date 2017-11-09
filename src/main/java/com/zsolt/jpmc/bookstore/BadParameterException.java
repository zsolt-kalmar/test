package com.zsolt.jpmc.bookstore;

public class BadParameterException extends Exception {

    public BadParameterException() {
    }

    public BadParameterException(String msg) {
        super(msg);
    }

    public BadParameterException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}