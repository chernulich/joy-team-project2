package com.coffeeshop.service.item;

import com.coffeeshop.converter.CommonProductItemConverter;
import com.coffeeshop.exception.ProductNotFoundException;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.model.entity.type.ProductStatus;
import com.coffeeshop.repository.ProductItemRepository;
import com.coffeeshop.repository.ProductQuantityRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ProductItemResponse> createProductItems(List<ProductItemRequest> productItemRequests) {
        for(ProductItemRequest productItemRequest : productItemRequests) {
            createProductItem(productItemRequest);
        }
        List<ProductItem> productItems = productItemRepository.findAll();
        return productItems.stream().map(item -> productItemConverter.getProductItemToProductItemResponse()
                .convert(item)).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductNotFoundException.class)
    public void createProductItem(ProductItemRequest productItemRequest) throws ProductNotFoundException {
        try {
            ProductItem productItem = productItemConverter.getProductItemRequestToProductItem().convert(productItemRequest);
            Product product = productRepository.findById(productItemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product doesn't exist by id: " + productItemRequest.getProductId()));
            productItem.setProduct(product);
            productItemRepository.save(productItem);
            productQuantityRepository.save(plusQuantity(product));
        }catch (ProductNotFoundException e) {
            e.httpStatus();
        }
    }

    public ProductQuantity plusQuantity(Product product) {
        ProductQuantity productQuantity = productQuantityRepository.findByProductId(product.getId());
        productQuantity.setQuantity(productQuantity.getQuantity() + 1);
        return productQuantity;
    }

    public ProductQuantity minusQuantity(ProductItem productItem) {
        ProductQuantity productQuantity = productQuantityRepository.findByProductId(productItem.getProduct().getId());
        productQuantity.setQuantity(productQuantity.getQuantity() - 1);
        return productQuantity;
    }

    @Override
    @Transactional
    public List<ProductItemResponse> findAndMarkAsSold(Long id) {
        ProductItem productItem = productItemRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product item doesn't exist by id: " + id));
        if(productItem.getVersion() == 1) {
            throw new OptimisticLockException("Product have sold yet.");
        }else {
            productItem.setProductStatus(ProductStatus.SOLD);
            productItemRepository.save(productItem);
            productQuantityRepository.save(minusQuantity(productItem));
        }
        List<ProductItem> productItems = productItemRepository.findAll();
        return productItems.stream().map(item -> productItemConverter.getProductItemToProductItemResponse()
        .convert(item)).collect(Collectors.toList());
    }
}
