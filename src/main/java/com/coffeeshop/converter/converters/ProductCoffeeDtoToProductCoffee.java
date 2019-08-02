package com.coffeeshop.converter.converters;

import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.coffeeshop.model.entity.ProductCoffee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCoffeeDtoToProductCoffee implements Converter<ProductCoffeeDto, ProductCoffee> {

    @Override
    public ProductCoffee convert(ProductCoffeeDto source) {
        return ProductCoffee.builder()
                .sour(source.getSour())
                .bitter(source.getBitter())
                .strong(source.getStrong())
                .ground(source.getGround())
                .decaf(source.getDecaf())
                .build();
    }
}
