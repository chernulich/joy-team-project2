package com.coffeeshop.service;

import com.coffeeshop.converter.CommonConverter;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductCoffee;
import com.coffeeshop.model.entity.ProductImage;
import com.coffeeshop.repository.ProductCoffeeRepository;
import com.coffeeshop.repository.ProductImageRepository;
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
    private ProductImageRepository productImageRepository;

    @Autowired
    private CommonConverter commonConverter;

    @Override
    @Transactional
    public void createProduct(ProductCreateRequest productCreateRequest) {
        Product product = commonConverter.getProductCreateDtoToProduct().convert(productCreateRequest);
        productRepository.save(product);
        ProductCoffee productCoffee = createProductCoffee(productCreateRequest);
        productCoffee.setProduct(product);
        productCoffeeRepository.save(productCoffee);
    }

    @Override
    public ProductCoffee createProductCoffee(ProductCreateRequest productCreateRequest) {
        ProductCoffee productCoffee = commonConverter.getProductCoffeeDtoToProductCoffee().convert(productCreateRequest.getProductCoffee());
        return productCoffee;
    }

    @Override
    @Transactional
    public void addProductImage(Long productId, byte[] image) {
        Product product = productRepository.getOne(productId);
        ProductImage productImage = ProductImage.builder()
                .product(product)
                .image(image)
                .build();
        productImageRepository.save(productImage);
    }

    @Override
    @Transactional
    public void makeAvailable(Long productId) {
        Product product = productRepository.getOne(productId);
        product.setAvailable(true);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void makeUnavailable(Long productId) {
        Product product = productRepository.getOne(productId);
        product.setAvailable(false);
        productRepository.save(product);
    }
}
