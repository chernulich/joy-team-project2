package com.coffeeshop.exception.order;

import com.coffeeshop.exception.BaseException;
import com.coffeeshop.model.web.error.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class OrderException extends BaseException {

    private Long id;
    private OrderExceptionType type;
    private static final String DEFAULT_MESSAGE = "Order exception";
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    private static final Map<OrderExceptionType, String> map = new HashMap<>();

    public OrderException(Long id, OrderExceptionType type) {
        this.id = id;
        this.type = type;
    }

    static {
        map.put(OrderExceptionType.ORDER_NOT_FOUND, "Order doesn't exist by this id.");
        map.put(OrderExceptionType.ORDER_DETAILS_NOT_FOUND, "Order details doesn't exist by this id.");
        map.put(OrderExceptionType.ORDER_EMAIL_NOT_FOUND, "Order email doesn't exist by this id.");
        map.put(OrderExceptionType.ORDER_ITEMS_NOT_FOUND, "Order item doesn't exist by this id.");
        map.put(OrderExceptionType.ORDER_PRICE_NOT_FOUND, "Order price doesn't exist by this id.");
    }

    @Override
    public ErrorResponse errorResponse() {
        return ErrorResponse.builder()
                .message(DEFAULT_MESSAGE)
                .errors(map.get(type) + id)
                .build();
    }

    @Override
    public HttpStatus httpStatus() {
        return BAD_REQUEST;
    }
}
