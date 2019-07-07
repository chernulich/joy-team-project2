package com.coffeeshop.exception;

import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public abstract ErrorResponse errorResponse();

    public abstract HttpStatus httpStatus();
}
