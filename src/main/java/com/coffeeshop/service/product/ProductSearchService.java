package com.coffeeshop.service.product;


import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.productDetails.RichProductResponse;

public interface ProductSearchService {

    RichProductResponse findProductById(Long id);

    ProductListResponse searchProductByParameters(ProductRequest productRequest);

}
