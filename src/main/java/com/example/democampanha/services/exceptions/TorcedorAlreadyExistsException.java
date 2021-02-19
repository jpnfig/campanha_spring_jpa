package com.example.democampanha.services.exceptions;

public class TorcedorAlreadyExistsException extends RuntimeException {

    public TorcedorAlreadyExistsException(Object id) {
        super("Torcedor já existe! Id: " + id);
    }
}
