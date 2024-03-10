package org.example.exception;

public class ListIsFullException extends RuntimeException{
    public ListIsFullException(String message){
        super(message);
    }
}
