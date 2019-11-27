package com.coffeeshop.exception.comments;

import com.coffeeshop.exception.BaseException;
import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public class CommentsException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Comment doesn't exist by this id.";

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    public CommentsException() {
        super(DEFAULT_MESSAGE);
    }

    public CommentsException(String message) {
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