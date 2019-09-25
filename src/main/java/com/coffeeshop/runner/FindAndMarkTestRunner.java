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
@Profile("test")
public class FindAndMarkTestRunner implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;

    public FindAndMarkTestRunner(ProductRepository productRepository, ProductItemRepository productItemRepository) {
        this.productRepository = productRepository;
        this.productItemRepository = productItemRepository;
    }


    @Override
    public void run(ApplicationArguments args) {

        Product product = createProduct("firstProduct");
        Product product2 = createProduct("secondProduct");
        Product product3 = createProduct("thirdProduct");
        Product product4 = createProduct("FourthProduct");

        List<ProductItem> itemList = new ArrayList<>();
        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        createProductItem(product, itemList, 100);
        createProductItem(product2, itemList, 99);
        createProductItem(product3, itemList, 100);
        createProductItem(product4, itemList, 5);

        productItemRepository.saveAll(itemList);
    }

    private Product createProduct(String s) {
        return Product.builder()
                .productName(s)
                .description(s)
                .previewImage(s)
                .available(true)
                .shortDescription("")
                .unitPrice(12.50)
                .build();
    }

    private void createProductItem(Product product, List<ProductItem> itemList, int amount) {
        for (int i = 0; i < amount; i++) {
            itemList.add(ProductItem.builder()
                    .product(product)
                    .weightKg(1)
                    .productStatus(ProductStatus.AVAILABLE)
                    .build());
        }
    }
}
