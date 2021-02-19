package com.example.democampanha.services.exceptions;

public class TorcedorAlreadyExistsException extends RuntimeException {

    public TorcedorAlreadyExistsException(Object email) {
        super("Torcedor já existe! Email: " + email);
    }
}
