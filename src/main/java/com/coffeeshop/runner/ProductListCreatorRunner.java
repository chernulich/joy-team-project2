package com.coffeeshop.runner;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductCoffee;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.model.entity.converter.CoffeeTypeConverter;
import com.coffeeshop.model.entity.type.ProductCategory;
import com.coffeeshop.repository.ProductCoffeeRepository;
import com.coffeeshop.repository.ProductQuantityRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Profile("dev")
public class ProductListCreatorRunner implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductCoffeeRepository productCoffeeRepository;
    private final ProductQuantityRepository productQuantityRepository;

    @Autowired
    public ProductListCreatorRunner(ProductRepository productRepository, ProductCoffeeRepository productCoffeeRepository, ProductQuantityRepository productQuantityRepository) {
        this.productRepository = productRepository;
        this.productCoffeeRepository = productCoffeeRepository;
        this.productQuantityRepository = productQuantityRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        List<Product> products = new ArrayList<>();
        List<ProductCoffee> productCoffees = new ArrayList<>();
        List<ProductQuantity> productQuantities = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Product product = Product.builder()
                    .productName("product#" + i)
                    .available(true)
                    .unitPrice(random.nextDouble() * 200)
                    .shortDescription("This is short description of product#" + i)
                    .description("This is short description of product#" + i)
                    .productCategory(ProductCategory.COFFEE)
                    .previewImage("")
                    .build();

            ProductCoffee productCoffee = ProductCoffee.builder()
                    .ground(random.nextBoolean())
                    .decaf(random.nextBoolean())
                    .bitter(random.nextInt(5) + 1)
                    .sour(random.nextInt(5) + 1)
                    .strong(random.nextInt(5) + 1)
                    .coffeeType(new CoffeeTypeConverter().convertToEntityAttribute(random.nextInt(4)+1))
                    .product(product)
                    .build();
            productCoffees.add(productCoffee);
            products.add(product);

            ProductQuantity productQuantity= ProductQuantity.builder()
                    .quantity(random.nextInt(100))
                    .product(product)
                    .build();

            productQuantities.add(productQuantity);
        }

        productRepository.saveAll(products);
        productQuantityRepository.saveAll(productQuantities);
        productCoffeeRepository.saveAll(productCoffees);
    }
}
