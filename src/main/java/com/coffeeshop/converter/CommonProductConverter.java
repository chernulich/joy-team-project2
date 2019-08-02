package com.coffeeshop.converter;

import com.coffeeshop.converter.converters.ProductCoffeeDtoToProductCoffee;
import com.coffeeshop.converter.converters.ProductCreateDtoToProduct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonProductConverter {

    private ProductCreateDtoToProduct productCreateDtoToProduct;

    private ProductCoffeeDtoToProductCoffee productCoffeeDtoToProductCoffee;

    @Autowired
    public CommonProductConverter(ProductCreateDtoToProduct productCreateDtoToProduct, ProductCoffeeDtoToProductCoffee productCoffeeDtoToProductCoffee) {
        this.productCreateDtoToProduct = productCreateDtoToProduct;
        this.productCoffeeDtoToProductCoffee = productCoffeeDtoToProductCoffee;
    }
}
