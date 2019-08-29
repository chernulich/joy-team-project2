package com.coffeeshop.service.product;


import com.coffeeshop.model.web.productDetails.RichProductResponse;

import java.util.List;

public interface ProductSearchService {

    RichProductResponse findProductById(Long id);

    List<RichProductResponse> searchProductByParameters();

}
