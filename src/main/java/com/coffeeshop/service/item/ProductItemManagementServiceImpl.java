package com.coffeeshop.service.item;

import com.coffeeshop.converter.CommonProductItemConverter;
import com.coffeeshop.exception.ProductException;
import com.coffeeshop.exception.ProductNotFoundException;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.repository.ProductItemRepository;
import com.coffeeshop.repository.ProductQuantityRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.coffeeshop.exception.ProductExceptionType.*;
import static com.coffeeshop.model.entity.type.ProductStatus.AVAILABLE;
import static com.coffeeshop.model.entity.type.ProductStatus.SOLD;
import static java.util.stream.Collectors.toList;

@Service
public class ProductItemManagementServiceImpl implements ProductItemManagementService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    private CommonProductItemConverter productItemConverter;

    @Override
    @Transactional
    public void createProductItems(List<ProductItemRequest> productItemRequests) {
        for(ProductItemRequest productItemRequest : productItemRequests) {
            createProductItem(productItemRequest);
        }
    }

    private void createProductItem(ProductItemRequest productItemRequest) {
        try {
            Product product = productRepository.findById(productItemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product doesn't exist by id: " + productItemRequest.getProductId()));
            Integer quantity = productItemRequest.getWeightKg();
            for(int i = quantity; i > 0; i--) {
                ProductItem productItem = productItemConverter.getProductItemRequestToProductItem().convert(productItemRequest);
                productItem.setWeightKg(1);
                productItem.setProduct(product);
                productItemRepository.save(productItem);
            }
            Long productId = product.getId();
            ProductQuantity productQuantity = productQuantityRepository.findByProductId(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product quantity doesn't exist by product id: " + productId));
            plusQuantity(productQuantity, quantity);
            productQuantityRepository.save(productQuantity);
        }catch (ProductNotFoundException e) {
            e.httpStatus();
        }
    }

    private void plusQuantity(ProductQuantity productQuantity, Integer quantity) {
        productQuantity.setQuantity(productQuantity.getQuantity() + quantity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ProductItem> findAndMarkAsSold(Product product, Integer amount) {

        List<ProductItem> result = productItemRepository.findProductItemByProductAndProductStatusLimitIs(product,
                AVAILABLE, amount);

        if (result.size() < amount) {
            throw new ProductException(product.getId(), ILLEGAL_QUANTITY);
        }

        for (ProductItem productItem : result) {
            productItem.setProductStatus(SOLD);
        }

        productItemRepository.saveAll(result);

        return result.size() == amount ? result : new ArrayList<>();
    }

    @Override
    @Transactional(rollbackFor = ProductException.class)
    @Retryable(
            value = org.hibernate.StaleObjectStateException.class,
            maxAttempts = 5,
            exclude = ProductException.class,
            backoff = @Backoff(delay = 500)
    )
    public List<ProductItemResponse> findAndMarkAsSold(Map<Long, Integer> items) {

        List<ProductItem> result = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            Product product = productRepository.findProductByIdAndAvailableIsTrue(entry.getKey())
                    .orElseThrow(() -> new ProductException(entry.getKey(), PRODUCT_NOT_AVAILABLE));
            List<ProductItem> markAsSoldList = findAndMarkAsSold(product, entry.getValue());

            if (markAsSoldList.isEmpty()) {
                throw new ProductException(entry.getKey(), OUT_OF_STOCK);
            }

            result.addAll(markAsSoldList);
        }

        return result.stream()
                .map(productItem -> productItemConverter.getProductItemToProductItemResponse().convert(productItem))
                .collect(toList());


    }
}
