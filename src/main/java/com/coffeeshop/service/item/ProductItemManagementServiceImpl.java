package com.coffeeshop.service.item;

import com.coffeeshop.converter.CommonProductItemConverter;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductNotFoundException.class)
    public void createProductItem(ProductItemRequest productItemRequest) throws ProductNotFoundException {
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

    private void minusQuantity(ProductQuantity productQuantity) {
        if(productQuantity.getQuantity() != 0) {
            productQuantity.setQuantity(productQuantity.getQuantity() - 1);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ProductItem> findAndMarkAsSold(Product product, Integer amount) {
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public List<ProductItemResponse> findAndMarkAsSold(Map<Long, Integer> items) {
        List<ProductItem> markedAsSoldItems = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            Product product = productRepository.findProductByIdAndAvailableIsTrue(entry.getKey()).orElseThrow(ProductNotFoundException::new);
            markedAsSoldItems.addAll(findAndMarkAsSold(product, entry.getValue()));
        }

        List<ProductItemResponse> result = new ArrayList<>();
        markedAsSoldItems.forEach(item -> productItemConverter.getProductItemToProductItemResponse().convert(item));

        return result;


    }
}
