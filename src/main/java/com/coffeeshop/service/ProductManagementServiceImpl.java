package com.coffeeshop.service;

import com.coffeeshop.converter.CommonConverter;
import com.coffeeshop.exception.ProductNotFoundException;
import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.model.admin.deserializer.CoffeeDeserializer;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductCoffee;
import com.coffeeshop.model.entity.ProductImage;
import com.coffeeshop.repository.ProductCoffeeRepository;
import com.coffeeshop.repository.ProductImageRepository;
import com.coffeeshop.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCoffeeRepository productCoffeeRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private CommonConverter commonConverter;

    @Override
    @Transactional
    public void createProduct(ProductCreateRequest productCreateRequest) throws IOException {
        Product product = commonConverter.getProductCreateDtoToProduct().convert(productCreateRequest);
        productRepository.save(product);
            ProductCoffeeDto productCoffeeDto = new ObjectMapper().readValue(productCreateRequest.getObject(), ProductCoffeeDto.class);
            ProductCoffee productCoffee = createProductCoffee(productCoffeeDto);
            productCoffee.setProduct(product);
            productCoffeeRepository.save(productCoffee);
    }

    public void addModule() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ProductCoffeeDto.class, new CoffeeDeserializer());
        mapper.registerModule(module);
    }

    @Override
    public ProductCoffee createProductCoffee(ProductCoffeeDto productCoffeeDto) {
        ProductCoffee productCoffee = commonConverter.getProductCoffeeDtoToProductCoffee().convert(productCoffeeDto);
        return productCoffee;
    }

    @Override
    @Transactional
    public void addProductImage(Long productId, byte[] image) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
        ProductImage productImage = ProductImage.builder()
                .product(product)
                .image(image)
                .build();
        productImageRepository.save(productImage);
    }

    @Override
    @Transactional
    public void makeAvailable(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
        product.setAvailable(true);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void makeUnavailable(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
        product.setAvailable(false);
        productRepository.save(product);
    }
}
