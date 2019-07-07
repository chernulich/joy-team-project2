package com.coffeeshop.exception;

import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.http.HttpStatus;

public class GeneralApiException extends BaseException {

    private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;


    public GeneralApiException() {
    }

    public GeneralApiException(String message) {
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
        return DEFAULT_HTTP_STATUS;
    }
}
