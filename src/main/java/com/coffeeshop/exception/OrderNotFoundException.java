package com.coffeeshop.exception;

import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BaseException {

    private static final String DEFAULT_MESSAGE = "Order doesn't exist by this id.";

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderNotFoundException(String message) {
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
