package com.coffeeshop.controller;

import com.coffeeshop.model.common.CoffeeType;
import com.coffeeshop.model.common.ProductType;
import com.coffeeshop.model.web.checkout.CheckoutRequest;
import com.coffeeshop.model.web.checkout.CheckoutResponse;
import com.coffeeshop.model.web.product.ProductDto;
import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductParametersResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.productDetails.CharacteristicResponse;
import com.coffeeshop.model.web.productDetails.InStockResponse;
import com.coffeeshop.model.web.productDetails.RichProductResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@RestController
@RequestMapping("/api/customer")
public class ProductController {

    @PostMapping("/products")
    public ProductListResponse getProductList(@RequestBody ProductRequest productRequest) {
        return ProductListResponse.builder()
                .popular(ProductDto.builder()
                        .productId(1L)
                        .title("Kenya AA")
                        .previewImage("image1")
                        .shortDescription("It is very good coffee!")
                        .price(30.00)
                        .inStockCount(20)
                        .type(ProductType.COFFEE.name())
                        .productParametersResponse(ProductParametersResponse.builder()
                                .flavour("strong")
                                .rate(1.0)
                                .coffeeType(CoffeeType.ARABICA.name().toLowerCase())
                                .decaf(true).build()).build())
                .products(Collections.singletonList(ProductDto.builder()
                        .productId(2L)
                        .title("Brazilian Santos")
                        .previewImage("image2")
                        .shortDescription("It is very good coffee too!")
                        .price(45.00)
                        .inStockCount(360)
                        .type(ProductType.COFFEE.name())
                        .productParametersResponse(ProductParametersResponse.builder()
                                .flavour("strong")
                                .rate(1.0)
                                .coffeeType(CoffeeType.ARABICA.name().toLowerCase())
                                .decaf(false).build()).build())).build();
    }

    @GetMapping("/products/{id}")
    public RichProductResponse getById(@PathVariable("id") Long productId) {
        String[] img = {"http://customer-ui/products/1/image/1", "http://customer-ui/products/1/image/1"};
        CharacteristicResponse characteristic = CharacteristicResponse.builder()
                .strong(2)
                .sour(2)
                .bitter(2)
                .build();
        InStockResponse inStock = InStockResponse.builder()
                .isAvailable(true)
                .quantityAvailable(20)
                .build();
        return RichProductResponse.builder()
                .productName("Alabasta")
                .quantityAvailableKg(300)
                .productImages(img)
                .characteristicResponse(characteristic)
                .description("!!!!")
                .inStockResponse(inStock)
                .unitPrice(100.0)
                .build();

    }

    @PostMapping("/checkout")
    public CheckoutResponse submitOrder(@RequestBody CheckoutRequest checkoutRequest) {
        return CheckoutResponse.builder()
                .orderId(1L)
                .message("Thanks for your order")
                .build();
    }


    @GetMapping(
            value = "/products/{productId}/images/{imageId}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProductImage(@PathVariable("productId") Long productId,
                                  @PathVariable("imageId") Long imageId) throws IOException {

        InputStream io = getClass()
                .getResourceAsStream("/image/coffee.jpg");
        return IOUtils.toByteArray(io);
    }
}

