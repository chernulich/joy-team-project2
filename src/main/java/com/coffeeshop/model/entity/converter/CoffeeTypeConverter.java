package com.coffeeshop.model.entity.converter;

import com.coffeeshop.model.common.CoffeeType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CoffeeTypeConverter implements AttributeConverter<CoffeeType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CoffeeType coffeeType) {
        return coffeeType == null ? CoffeeType.ARABICA.getId() : coffeeType.getId();
    }

    @Override
    public CoffeeType convertToEntityAttribute(Integer id) {
        return id == null ? CoffeeType.ARABICA : CoffeeType.getById(id);
    }
}
