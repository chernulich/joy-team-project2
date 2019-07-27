package com.coffeeshop.exception;

import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException {

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse errorResponse() {
        return ErrorResponse.builder()
                .message(this.getMessage())
                .build();
    }

    @Override
    public HttpStatus httpStatus() {
        return BAD_REQUEST;
    }
}
