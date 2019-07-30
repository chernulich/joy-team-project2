package com.coffeeshop.service;

import com.coffeeshop.converter.CommonConverter;
import com.coffeeshop.exception.ProductNotFoundException;
import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductCoffee;
import com.coffeeshop.repository.ProductCoffeeRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCoffeeRepository productCoffeeRepository;


    @Autowired
    private CommonConverter commonConverter;

    @Override
    @Transactional
    public void createProduct(ProductCreateRequest productCreateRequest)  {
        Product product = commonConverter.getProductCreateDtoToProduct().convert(productCreateRequest);
        productRepository.save(product);
        ProductCoffee productCoffee = createProductCoffee(productCreateRequest.getProductCoffeeDto());
        productCoffee.setProduct(product);
        productCoffeeRepository.save(productCoffee);
    }


    @Override
    @Transactional
    public ProductCoffee createProductCoffee(ProductCoffeeDto productCoffeeDto) {
        ProductCoffee productCoffee = commonConverter.getProductCoffeeDtoToProductCoffee().convert(productCoffeeDto);
        return productCoffee;
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
