package com.example.democampanha.services.exceptions;

public class AssociationAlreadyExistsException extends RuntimeException {

    public AssociationAlreadyExistsException(Object idCampanha, Object idTorcedor) {

        super("Torcedor já associado a campanha. Torcedor: " + idTorcedor + " Campanha: " + idCampanha);
    }
}
