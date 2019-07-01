package com.coffeeshop.controller;

import com.coffeeshop.model.common.CoffeeType;
import com.coffeeshop.model.common.ProductType;
import com.coffeeshop.model.web.product.ProductDto;
import com.coffeeshop.model.web.product.ProductListResponseDto;
import com.coffeeshop.model.web.product.ProductParametersDto;
import com.coffeeshop.model.web.product.ProductRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class ProductController {

    @PostMapping("/products")
    public ProductListResponseDto getProductList(@RequestBody ProductRequestDto productRequestDto){
        return ProductListResponseDto.builder()
                .popular(ProductDto.builder()
                        .productId(1L)
                        .title("Kenya AA")
                        .previewImage("image1")
                        .shortDescription("It is very good coffee!")
                        .price(30.00)
                        .inStockCount(20)
                        .type(ProductType.COFFEE.name())
                        .productParameters(ProductParametersDto.builder()
                                .flavour("strong")
                                .rate(1.0)
                                .coffeeType(CoffeeType.ARABICA.name().toLowerCase())
                                .decaf(true).build()).build())
                .products(Arrays.asList(ProductDto.builder()
                        .productId(2L)
                        .title("Brazilian Santos")
                        .previewImage("image2")
                        .shortDescription("It is very good coffee too!")
                        .price(45.00)
                        .inStockCount(360)
                        .type(ProductType.COFFEE.name())
                        .productParameters(ProductParametersDto.builder()
                                .flavour("strong")
                                .rate(1.0)
                                .coffeeType(CoffeeType.ARABICA.name().toLowerCase())
                                .decaf(false).build()).build())).build();
    }
}
