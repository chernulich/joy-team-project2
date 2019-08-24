package com.coffeeshop.repository.search;

import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;

public interface ProductSearchRepository {
    public ProductListResponse getProductsViaSearchProductRequest(ProductRequest request);
}
