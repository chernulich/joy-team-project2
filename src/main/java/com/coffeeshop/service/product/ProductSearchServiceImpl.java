package com.coffeeshop.service.product;

import com.coffeeshop.exception.*;
import com.coffeeshop.model.entity.*;
import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.productDetails.CharacteristicResponse;
import com.coffeeshop.model.web.productDetails.RichProductResponse;
import com.coffeeshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;
    private final ProductQuantityRepository productQuantityRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductCoffeeRepository productCoffeeRepository;

    @Autowired
    public ProductSearchServiceImpl(ProductRepository productRepository,
                                    ProductQuantityRepository productQuantityRepository,
                                    ProductImageRepository productImageRepository,
                                    ProductCoffeeRepository productCoffeeRepository) {

        this.productRepository = productRepository;
        this.productQuantityRepository = productQuantityRepository;
        this.productImageRepository = productImageRepository;
        this.productCoffeeRepository = productCoffeeRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public RichProductResponse findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        ProductQuantity productQuantity = productQuantityRepository.findProductQuantityByProduct(product)
                .orElseThrow(ProductQuantityNotFoundException::new);

        List<ProductImage> productImages = productImageRepository.findProductImagesByProduct(product)
                .orElse(new ArrayList<>());

        ProductCoffee productCoffee = productCoffeeRepository.findProductCoffeeByProduct(product)
                .orElseThrow(ProductCoffeeNotFoundException::new);

        return convertProductToRichProductResponse(product, productQuantity,
                productCoffee, productImages);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductListResponse searchProductByParameters(ProductRequest productRequest) {
        return productRepository.searchProductsViaParams(productRequest);
    }

    private RichProductResponse convertProductToRichProductResponse(Product product, ProductQuantity productQuantity,
                                                                    ProductCoffee productCoffee,
                                                                    List<ProductImage> productImages) {

        CharacteristicResponse characteristicResponse = CharacteristicResponse.builder()
                .bitter(productCoffee.getBitter())
                .sour(productCoffee.getSour())
                .strong(productCoffee.getStrong())
                .build();

        String[] imageLinks = createImageLinks(product.getId(), productImages.size());


        return RichProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .shortDescription(product.getShortDescription())
                .productImages(imageLinks)
                .previewImage(new String())
                .characteristicResponse(characteristicResponse)
                .amountAvailable(productQuantity.getQuantity())
                .price(product.getUnitPrice())
                .build();
    }

    private static String[] createImageLinks(Long productId, int countOfImages) {

        String[] links = new String[countOfImages];

        for (int i = 0; i < countOfImages; i++) {
            links[i] = String.format("/api/customer/products/%d/images/%d", productId, i + 1);
        }

        return links;
    }
}

