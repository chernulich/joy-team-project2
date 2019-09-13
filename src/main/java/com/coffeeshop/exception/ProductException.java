package com.coffeeshop.exception;

import com.coffeeshop.model.web.error.ErrorResponse;
import com.coffeeshop.model.web.error.ProductExceptionResponse;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ProductException extends BaseException {


    public Long productId;
    private final String DEFAULT_MESSAGE = "Product checkout error";
    private static final HttpStatus PRECONDITION_FAILED = HttpStatus.PRECONDITION_FAILED;
    private ProductExceptionType type;

    public static final Map<ProductExceptionType, String> map = new HashMap();

    static {
        map.put(ProductExceptionType.ILLEGAL_QUANTITY,"Illegal quantity");
        map.put(ProductExceptionType.OUT_OF_STOCK,"Product is out of stock");
        map.put(ProductExceptionType.PRODUCT_NOT_AVAILABLE,"Product is not in sale or does not exist");
    }

    public ProductException(Long productId, ProductExceptionType  type) {
        this.productId = productId;
        this.type = type;
    }

    @Override
    public ErrorResponse errorResponse() {

        return ErrorResponse.builder()
                .message(DEFAULT_MESSAGE)
                .errors(new ProductExceptionResponse(productId,map.get(type)))
                .build();
    }

    @Override
    public HttpStatus httpStatus() {
        return PRECONDITION_FAILED;
    }
}
