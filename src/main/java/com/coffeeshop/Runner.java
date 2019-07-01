package com.coffeeshop;

import com.coffeeshop.model.entity.Characteristic;
import com.coffeeshop.model.entity.Example;
import com.coffeeshop.model.entity.InStock;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.repository.CharacteristicRepository;
import com.coffeeshop.repository.ExampleRepository;
import com.coffeeshop.repository.InStockRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private ExampleRepository exampleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CharacteristicRepository characteristicRepository;
    @Autowired
    private InStockRepository inStockRepository;




    @Override
    public void run(String... args) throws Exception {
//        exampleRepository.saveAll(
//                Stream.of(Example.builder()
//                                .name("Sarah")
//                                .build(),
//                        Example.builder()
//                                .name("David")
//                                .build()
//                )
//                        .collect(Collectors.toList()));
        String[] img = {"http://customer-ui/products/1/image/1", "http://customer-ui/products/1/image/1"};
        Product product = Product.builder()
                .productName("Alabasta")
                .quantityAvailableKg(300)
                .productImages(img)
                .description("!!!!")
                .unitPrice(100.0)
                .build();
        productRepository.save(product);
        characteristicRepository.save(Characteristic.builder()
                .product(product)
                .strong(2)
                .sour(2)
                .bitter(2)
                .build());
        inStockRepository.save(InStock.builder()
                .product(product)
                .isAvailable(true)
                .quantityAvailable(20)
                .build());
    }
}
