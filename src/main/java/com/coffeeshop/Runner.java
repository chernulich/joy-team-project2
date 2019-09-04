package com.coffeeshop;

import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.entity.type.ProductCategory;
import com.coffeeshop.service.item.ProductItemManagementService;
import com.coffeeshop.service.product.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class Runner implements CommandLineRunner {

    @Autowired
    private ProductManagementService productManagementService;

    @Autowired
    private ProductItemManagementService productItemManagementService;

    @Override
    public void run(String... args) throws Exception {

        ProductCoffeeDto productCoffeeDto1 = ProductCoffeeDto.builder()
                .bitter(3)
                .sour(3)
                .strong(1)
                .decaf(false)
                .ground(true)
                .build();

        ProductCoffeeDto productCoffeeDto2 = ProductCoffeeDto.builder()
                .bitter(4)
                .sour(4)
                .strong(5)
                .decaf(false)
                .ground(false)
                .build();

        productManagementService.createProductAndQuantity(ProductCreateRequest.builder()
                .productName("Alabasta")
                .shortDescription("Best coffee in the world")
                .description("Product description")
                .productCategory(ProductCategory.COFFEE)
                .previewImage("http://customer-ui/products/1/image/1")
                .unitPrice(159.99)
                .productCoffeeDto(productCoffeeDto1)
                .build());

        productManagementService.createProductAndQuantity(ProductCreateRequest.builder()
                .productName("Mary-Geoise")
                .shortDescription("Best coffee in the world")
                .description("Mary-Geoise is located on the Red Line " +
                        "and serves as the official route for people to " +
                        "cross between the two halves of the Grand Line, " +
                        "although only people acting inside the law are allowed passage;")
                .productCategory(ProductCategory.COFFEE)
                .previewImage("http://customer-ui/products/2/image/2")
                .unitPrice(78.57)
                .productCoffeeDto(productCoffeeDto1)
                .build());

        ProductItemRequest productItemRequest1 = ProductItemRequest.builder()
                .productId(1L)
                .weightKg(30)
                .build();

        ProductItemRequest productItemRequest2 = ProductItemRequest.builder()
                .productId(2L)
                .weightKg(30)
                .build();

        List<ProductItemRequest> productItemRequests = new ArrayList<>();

        productItemRequests.add(productItemRequest1);
        productItemRequests.add(productItemRequest2);

        productItemManagementService.createProductItems(productItemRequests);


    }
}
