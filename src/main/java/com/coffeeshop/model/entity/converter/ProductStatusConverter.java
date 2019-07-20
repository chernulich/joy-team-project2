package com.coffeeshop.model.entity.converter;


import com.coffeeshop.model.entity.type.ProductStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<ProductStatus,Integer> {


    @Override
    public Integer convertToDatabaseColumn(ProductStatus productStatus) {
        return productStatus == null ? ProductStatus.AVAILABLE.getId() : productStatus.getId();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer id) {
        return id == null ? ProductStatus.AVAILABLE : ProductStatus.getById(id);
    }
}
