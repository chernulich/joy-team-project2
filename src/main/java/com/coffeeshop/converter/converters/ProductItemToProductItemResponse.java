package com.coffeeshop.converter.converters;

import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.model.entity.ProductItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductItemToProductItemResponse implements Converter<ProductItem, ProductItemResponse> {

    @Override
    public ProductItemResponse convert(ProductItem source) {
        return ProductItemResponse.builder()
                .id(source.getId())
                .productId(source.getProduct().getId())
                .weightKG(source.getWeightKg())
                .status(source.getProductStatus())
                .build();
    }
}
