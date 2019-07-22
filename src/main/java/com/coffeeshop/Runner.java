package com.coffeeshop;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.repository.OrderDetailsRepository;
import com.coffeeshop.repository.OrderPriceRepository;
import com.coffeeshop.repository.ProductQuantityRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    private OrderPriceRepository orderPriceRepository;

    @Override
    public void run(String... args) throws Exception {

        Product product = new Product();
        product.setDescription("sasha");
        product.setShortDescription("sasha");
        product.setPreviewImage("sasha");
        product.setUnitPrice(12.32);

        productRepository.save(product);

        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setProduct(product);
        productQuantityRepository.save(productQuantity);

    }
}
