package com.coffeeshop.service;

import com.coffeeshop.converter.CommonConverter;
import com.coffeeshop.model.admin.ProductCreateDto;
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
    public Product createProduct(ProductCreateDto productCreateDto) {
        Product product = commonConverter.getProductCreateDtoToProduct().convert(productCreateDto);
        productRepository.save(product);
        ProductCoffee productCoffee = createProductCoffee(productCreateDto);
        productCoffee.setProduct(product);
        return product;
    }

    @Override
    public ProductCoffee createProductCoffee(ProductCreateDto productCreateDto) {
        ProductCoffee productCoffee = commonConverter.getProductCoffeeDtoToProductCoffee().convert(productCreateDto.getProductCoffee());
        productCoffeeRepository.save(productCoffee);
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
}
