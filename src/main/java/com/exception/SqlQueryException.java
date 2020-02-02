package com.exception;

public class SqlQueryException extends RuntimeException {

    public SqlQueryException(String exception){
        super(exception);
    }
}
