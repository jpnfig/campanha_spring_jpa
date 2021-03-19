package com.example.democampanha.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TorcedorAlreadyExistsException.class)
    public ResponseEntity<StandardError> torcedorAlreadyExists(TorcedorAlreadyExistsException e,
                                                             HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidAssociationException.class)
    public ResponseEntity<StandardError> invalidAssociation(InvalidAssociationException e,
                                                             HttpServletRequest request){
        String error = "Validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AssociationAlreadyExistsException.class)
    public ResponseEntity<StandardError> associationAlreadyExists(AssociationAlreadyExistsException e,
                                                             HttpServletRequest request){
        String error = "Validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationFieldsRequest(MethodArgumentNotValidException e,
                                                           HttpServletRequest request){
        String error = "Validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> camposComErro = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(objectError -> {
            String campo = ((FieldError) objectError).getField();
            String messageErro = objectError.getDefaultMessage();

            camposComErro.put(campo, messageErro);
        });

        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                camposComErro.toString(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> validationDataRequest(HttpMessageNotReadableException e,
                                                               HttpServletRequest request){
        String error = "Validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                "Campo time do coração inválido.",
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> validationAssociationRequest(NoSuchElementException e,
                                                                      HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                "Falha na associação, verifique a campanha e o torcedor.",
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> validationRequest(MethodArgumentTypeMismatchException e,
                                                           HttpServletRequest request){
        String error = "Validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                "Dado(s) de entrada inválido(s)",
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<StandardError> validationRequest(DataNotValidException e,
                                                           HttpServletRequest request){
        String error = "Validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
