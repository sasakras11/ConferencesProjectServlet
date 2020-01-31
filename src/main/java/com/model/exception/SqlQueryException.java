package com.model.exception;

public class SqlQueryException extends RuntimeException {

    public SqlQueryException(String msg){
        super(msg);
    }
}
