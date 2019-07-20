package com.coffeeshop.model.entity.converter;

import com.coffeeshop.model.entity.type.ProductCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductCategoryConverter implements AttributeConverter<ProductCategory, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductCategory productCategory) {
        return productCategory == null ? ProductCategory.COFFEE.getId() : productCategory.getId();
    }

    @Override
    public ProductCategory convertToEntityAttribute(Integer id) {
        return id == null ? ProductCategory.COFFEE : ProductCategory.getById(id);
    }
}
