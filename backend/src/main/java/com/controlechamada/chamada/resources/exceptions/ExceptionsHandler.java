package com.controlechamada.chamada.resources.exceptions;

import com.controlechamada.chamada.services.exceptions.NotFoundException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException e, HttpServletRequest req){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError("Resource not found", e.getMessage(), status.value(), Instant.now(), req.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<StandardError> propertyBadRequest(PropertyValueException e, HttpServletRequest req){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError("Bad Request", e.getMessage(), status.value(), Instant.now(), req.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
