package com.example.democampanha.exceptions;

public class DataNotValidException extends RuntimeException {
    public DataNotValidException(String message){
        super(message);
    }
}
