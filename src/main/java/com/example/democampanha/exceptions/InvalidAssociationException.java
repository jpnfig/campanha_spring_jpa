package com.example.democampanha.exceptions;

public class InvalidAssociationException extends RuntimeException {

    public InvalidAssociationException(Object timeTorcedor, Object timeCampanha) {
        super
                ("Associação inválida. Divergência entre o time do torcedor e o time da campanha: " + timeTorcedor +
                        " Time da campanha: " + timeCampanha );
    }
}
