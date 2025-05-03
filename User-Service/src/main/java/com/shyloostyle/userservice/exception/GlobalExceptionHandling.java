package com.shyloostyle.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler({UserNotFoundException.class,
            UserAlreadyExistsException.class})
    public ResponseEntity<ErrorMessage> handlerUserNotFoundException(UserNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getMessage(),HttpStatus.NOT_FOUND.toString(),
                String.valueOf(System.currentTimeMillis())
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                String.valueOf(System.currentTimeMillis())
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
