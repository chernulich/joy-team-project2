package com.coffeeshop.job;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.model.entity.type.ProductStatus;
import com.coffeeshop.repository.ProductItemRepository;
import com.coffeeshop.repository.ProductQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductQuantityJob {

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @Transactional
    @Scheduled(fixedRate = 120000)
    public void productQuantityJob() {
        List<ProductQuantity> productQuantities = productQuantityRepository.findAll().stream()
                .filter(productQuantity -> checkQuantity(productQuantity))
                .map(productQuantity -> updateQuantity(productQuantity)).collect(Collectors.toList());
        productQuantityRepository.saveAll(productQuantities);
    }

    private Integer getAvailableAmount(ProductQuantity productQuantity) {
        Product product = productQuantity.getProduct();
        ProductStatus status = ProductStatus.AVAILABLE;
        List<ProductItem> productItems = productItemRepository.findAllByProductStatusAndProduct(status, product);
        return productItems.size();
    }

    private boolean checkQuantity(ProductQuantity productQuantity) {
        Integer availableAmount = getAvailableAmount(productQuantity);
        Integer quantity = productQuantity.getQuantity();
        return availableAmount < quantity;
    }

    private ProductQuantity updateQuantity(ProductQuantity productQuantity) {
        Integer availableAmount = getAvailableAmount(productQuantity);
        productQuantity.setQuantity(availableAmount);
        return productQuantity;
    }
}
