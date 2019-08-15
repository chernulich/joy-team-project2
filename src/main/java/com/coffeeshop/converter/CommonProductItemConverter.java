package com.coffeeshop.converter;

import com.coffeeshop.converter.converters.ProductItemRequestToProductItem;
import com.coffeeshop.converter.converters.ProductItemToProductItemResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonProductItemConverter {

    private ProductItemToProductItemResponse productItemToProductItemResponse;

    private ProductItemRequestToProductItem productItemRequestToProductItem;

    @Autowired
    public CommonProductItemConverter(ProductItemToProductItemResponse productItemToProductItemResponse, ProductItemRequestToProductItem productItemRequestToProductItem) {
        this.productItemToProductItemResponse = productItemToProductItemResponse;
        this.productItemRequestToProductItem = productItemRequestToProductItem;
    }
}
