package com.coffeeshop.converter.converters;

import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.entity.ProductItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductItemRequestToProductItem implements Converter<ProductItemRequest, ProductItem> {

    @Override
    public ProductItem convert(ProductItemRequest source) {
        return ProductItem.builder()
                .weightKg(source.getWeightKg())
                .build();
    }
}
