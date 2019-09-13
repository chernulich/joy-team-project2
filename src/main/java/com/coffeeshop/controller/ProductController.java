package com.coffeeshop.controller;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.web.checkout.CheckoutRequest;
import com.coffeeshop.model.web.checkout.CheckoutResponse;
import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.productDetails.CharacteristicResponse;
import com.coffeeshop.model.web.productDetails.RichProductResponse;
import com.coffeeshop.repository.search.ProductSearchRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class ProductController {

    private final ProductSearchRepository productSearchRepository;

    @PostMapping("/products")
    public ProductListResponse getProductList(@RequestBody @Valid ProductRequest productRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return productSearchRepository.searchProductsViaParams(productRequest);
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

