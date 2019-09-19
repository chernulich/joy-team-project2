package com.coffeeshop.controller.customer;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.web.checkout.CheckoutRequest;
import com.coffeeshop.model.web.checkout.CheckoutResponse;
import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.productDetails.CharacteristicResponse;
import com.coffeeshop.model.web.productDetails.RichProductResponse;
import com.coffeeshop.service.product.ProductSearchService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/customer")
@PropertySource(value = "classpath:defaultDtoValues.properties")
public class ProductController {

    @Value(value = "${default.page.size}")
    private Integer defaultPageSize;

    @Value(value = "${default.result.size}")
    private Integer defaultResultSize;

    @Value(value = "${default.max.result.size}")
    private Integer defaultMaxResultSize;

    private final ProductSearchService productSearchService;

    @Autowired
    public ProductController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @PostMapping("/products")
    public ProductListResponse getProductList(@RequestBody @Valid ProductRequest productRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }

        if(productRequest.getPage() == null){
            productRequest.setPage(defaultPageSize);
        }

        if (productRequest.getResults() == null) {
            productRequest.setResults(defaultResultSize);
        } else if (productRequest.getResults() > 20) {
            productRequest.setResults(defaultMaxResultSize);
        }

        return productSearchService.searchProductByParameters(productRequest);
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
