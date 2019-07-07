package com.coffeeshop.controller;


import com.coffeeshop.model.common.CoffeeType;
import com.coffeeshop.model.common.ProductType;
import com.coffeeshop.model.web.checkout.CheckoutDtoResponse;
import com.coffeeshop.model.web.checkout.CustomerInfoDtoRequest;
import com.coffeeshop.model.web.product.ProductDto;
import com.coffeeshop.model.web.product.ProductListResponseDto;
import com.coffeeshop.model.web.product.ProductParametersDto;
import com.coffeeshop.model.web.product.ProductRequestDto;
import com.coffeeshop.model.web.productDetails.CharacteristicDtoResponse;
import com.coffeeshop.model.web.productDetails.InStockDtoResponse;
import com.coffeeshop.model.web.productDetails.RichProductDtoResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/api/customer")
public class ProductController {

    @PostMapping("/products")
    public ProductListResponseDto getProductList(@RequestBody ProductRequestDto productRequestDto) {
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

    @GetMapping("/products/{id}")
    public RichProductDtoResponse getById(@PathVariable("id") Long productId) {
        String[] img = {"http://customer-ui/products/1/image/1", "http://customer-ui/products/1/image/1"};
        CharacteristicDtoResponse characteristic = CharacteristicDtoResponse.builder()
                .strong(2)
                .sour(2)
                .bitter(2)
                .build();
        InStockDtoResponse inStock = InStockDtoResponse.builder()
                .isAvailable(true)
                .quantityAvailable(20)
                .build();
        return RichProductDtoResponse.builder()
                .productName("Alabasta")
                .quantityAvailableKg(300)
                .productImages(img)
                .characteristicDtoResponse(characteristic)
                .description("!!!!")
                .inStockDtoResponse(inStock)
                .unitPrice(100.0)
                .build();
    }

    @PostMapping("/checkout")
    public CheckoutDtoResponse submitOrder(@RequestBody CustomerInfoDtoRequest customerInfoDtoRequest) {
        return CheckoutDtoResponse.builder()
                .orderId(1L)
                .message("Thanks for your order")
                .build();
    }

    @GetMapping(
            value = "/products/{productId}/images/{imageId}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getProductImage(@PathVariable("productId") Long productId,
                           @PathVariable("imageId") Long imageId) throws IOException {

        InputStream io = getClass()
                .getResourceAsStream("/image/coffee.jpg");
        return IOUtils.toByteArray(io);
    }
}

