package com.example.myfinancehub.exceptions.transactions;

public class TransactionConflictException extends RuntimeException {
    public TransactionConflictException(String message) {
        super(message);
    }
}
