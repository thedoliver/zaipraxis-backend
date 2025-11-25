package br.com.zaipraixis.services.exceptions;

public class DuplicateKeyException extends RuntimeException  {

    public DuplicateKeyException(String message, Throwable cause){
        super(message, cause);
    }

    public DuplicateKeyException(String message){
        super(message);
    }

}