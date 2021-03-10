package com.example.democampanha.services.exceptions;

public class InvalidAssociationException extends RuntimeException {

    public InvalidAssociationException(Object timeTorcedor, Object timeCampanha) {
        super
                ("Associação inválida! Time do torcedor: " + timeTorcedor +
                        " Time da campanha: " + timeCampanha );
    }
}
