package com.coffeeshop.exception;

import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ProductImageNotFoundException extends BaseException {

    private static final String DEFAULT_MESSAGE = "Product image doesn't exist by this id.";

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    public ProductImageNotFoundException() {
    }

    public ProductImageNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse errorResponse() {
        return ErrorResponse.builder()
                .message(DEFAULT_MESSAGE)
                .build();
    }

    @Override
    public HttpStatus httpStatus() {
        return BAD_REQUEST;
    }
}
