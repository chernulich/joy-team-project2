package com.coffeeshop.runner;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.type.ProductStatus;
import com.coffeeshop.repository.ProductItemRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Profile("dev")
public class FindAndMarkTestRunner implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;

    public FindAndMarkTestRunner(ProductRepository productRepository, ProductItemRepository productItemRepository) {
        this.productRepository = productRepository;
        this.productItemRepository = productItemRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Product product = Product.builder()
                .productName("1")
                .description("1")
                .previewImage("1")
                .available(true)
                .shortDescription("")
                .unitPrice(12.50)
                .build();
        Product product2 = Product.builder()
                .productName("12")
                .description("12")
                .previewImage("12")
                .available(true)
                .shortDescription("")
                .unitPrice(12.50)
                .build();

        List<ProductItem> itemList = new ArrayList<>();
        productRepository.save(product);
        productRepository.save(product2);

        for (int i = 0; i < 100; i++) {
            itemList.add(ProductItem.builder()
                    .product(product)
                    .weightKg(1)
                    .productStatus(ProductStatus.AVAILABLE)
                    .build());
        }
        for (int i = 0; i < 5; i++) {
            itemList.add(ProductItem.builder()
                    .product(product2)
                    .weightKg(1)
                    .productStatus(ProductStatus.AVAILABLE)
                    .build());
        }

        productItemRepository.saveAll(itemList);
    }
}
