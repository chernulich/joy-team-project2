package com.coffeeshop.exception;

import com.coffeeshop.model.web.error.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputValidationException extends BaseException {

    private static final String DEFAULT_MESSAGE = "Input validation error";

    private BindingResult bindingResult;

    private Map<String, List<String>> errors;

    public InputValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public InputValidationException(Map<String, List<String>> errors) {
        this.errors = errors;
    }


    @Override
    public ErrorResponse errorResponse() {
        return ErrorResponse.builder()
                .message(DEFAULT_MESSAGE)
                .errors(getErrors())
                .build();
    }

    public Map<String, List<String>> getErrors() {
        if (errors != null) {
            return errors;
        }

        return bindingResult.getFieldErrors()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage,
                                        Collectors.toList())
                        )
                );
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
