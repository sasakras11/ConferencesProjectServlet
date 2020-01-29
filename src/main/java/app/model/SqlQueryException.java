package app.model;

public class SqlQueryException extends RuntimeException {

    public SqlQueryException(String msg){
        super(msg);
    }
}
