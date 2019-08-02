package com.coffeeshop.converter.converters;

import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.model.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateDtoToProduct implements Converter<ProductCreateRequest, Product> {

    @Override
    public Product convert(ProductCreateRequest source) {
        return Product.builder()
                .shortDescription(source.getShortDescription())
                .description(source.getDescription())
                .previewImage(source.getPreviewImage())
                .unitPrice(source.getUnitPrice())
                .productCategory(source.getProductCategory())
                .available(false)
                .build();
    }
}
