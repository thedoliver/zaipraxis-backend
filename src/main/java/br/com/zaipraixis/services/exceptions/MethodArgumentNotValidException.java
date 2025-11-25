package br.com.zaipraixis.services.exceptions;

public class MethodArgumentNotValidException extends RuntimeException  {

    public MethodArgumentNotValidException(String message, Throwable cause){
        super(message, cause);
    }
    public MethodArgumentNotValidException(String message){
        super(message);
    }

}
