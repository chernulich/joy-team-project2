package com.coffeeshop.handler;

import com.coffeeshop.exception.BaseException;
import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ErrorHandlerAdvice {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> getResponse(Exception e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .build();

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> getResponse(ResponseStatusException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> getResponse(BaseException e) {

        return new ResponseEntity<>(e.errorResponse(), e.httpStatus());

    }
}
