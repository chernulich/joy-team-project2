package com.coffeeshop.controller;

import com.coffeeshop.exception.BaseException;
import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.web.checkout.CheckoutRequest;
import com.coffeeshop.model.web.checkout.CheckoutResponse;
import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.productDetails.CharacteristicResponse;
import com.coffeeshop.model.web.productDetails.RichProductResponse;
import com.coffeeshop.repository.search.ProductSearchRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/customer")
public class ProductController {

    @Autowired
    private ProductSearchRepository searchRepository;

    @PostMapping("/products")
    public ProductListResponse getProductList(@RequestBody ProductRequest productRequest){
        return searchRepository.searchProductsViaParams(productRequest);
//        return ProductListResponse.builder()
//                .popular(ProductResponse.builder()
//                        .productId(1L)
//                        .title("Kenya AA")
//                        .previewImage("image1")
//                        .shortDescription("It is very good coffee!")
//                        .price(30.00)
//                        .availableAmount(20)
//                        .type(ProductType.COFFEE.name())
//                        .productParametersResponse(ProductParametersResponse.builder()
//                                .sour(1)
//                                .bitter(3)
//                                .strong(5)
//                                .coffeeType(CoffeeType.ARABICA.name().toLowerCase())
//                                .decaf(true).build()).build())
//                .products(Collections.singletonList(ProductResponse.builder()
//                        .productId(2L)
//                        .title("Brazilian Santos")
//                        .previewImage("image2")
//                        .shortDescription("It is very good coffee too!")
//                        .price(45.00)
//                        .availableAmount(360)
//                        .type(ProductType.COFFEE.name())
//                        .productParametersResponse(ProductParametersResponse.builder()
//                                .sour(2)
//                                .bitter(2)
//                                .strong(2)
//                                .coffeeType(CoffeeType.ARABICA.name().toLowerCase())
//                                .decaf(false).build()).build())).build();
    }

    @GetMapping("/products/{id}")
    public RichProductResponse getById(@PathVariable("id") Long productId) {
        String[] img = {"http://customer-ui/products/1/image/1", "http://customer-ui/products/1/image/1"};
        CharacteristicResponse characteristic = CharacteristicResponse.builder()
                .strong(2)
                .sour(2)
                .bitter(2)
                .build();

        return RichProductResponse.builder()
                .productName("Alabasta")
                .amountAvailable(300)
                .productImages(img)
                .characteristicResponse(characteristic)
                .description("!!!!")
                .shortDescription("!!")
                .price(100.0)
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

