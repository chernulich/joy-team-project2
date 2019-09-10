package com.coffeeshop.model.web.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductExceptionResponse {

    private Long productId;
    private String message;

}
