package com.coffeeshop.controller;


import com.coffeeshop.model.web.productDetails.CharacteristicDtoResponse;
import com.coffeeshop.model.web.productDetails.InStockDtoResponse;
import com.coffeeshop.model.web.productDetails.RichProductDtoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/customer")
public class ProductController {


    @GetMapping("/products/{id}")
    public RichProductDtoResponse getById(@PathVariable("id") Long productId) {
        String[] img = {"http://customer-ui/products/1/image/1", "http://customer-ui/products/1/image/1"};
        List<CharacteristicDtoResponse> characteristic = Stream.of(CharacteristicDtoResponse.builder()
                .strong(2)
                .sour(2)
                .bitter(2)
                .build()).collect(Collectors.toList());
        List<InStockDtoResponse> inStock = Stream.of(InStockDtoResponse.builder()
                .isAvailable(true)
                .quantityAvailable(20)
                .build()).collect(Collectors.toList());
        return RichProductDtoResponse.builder()
                .productName("Alabasta")
                .quantityAvailableKg(300)
                .productImages(img)
                .characteristicDtoResponseList(characteristic)
                .description("!!!!")
                .inStockDtoResponseList(inStock)
                .unitPrice(100.0)
                .build();
    }


}
