package com.coffeeshop.exception.user;

import com.coffeeshop.exception.BaseException;
import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public class UsersException extends BaseException {

    private static final String DEFAULT_MESSAGE = "User doesn't exist by this id.";

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    public UsersException() {
        super(DEFAULT_MESSAGE);
    }

    public UsersException(String message) {
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