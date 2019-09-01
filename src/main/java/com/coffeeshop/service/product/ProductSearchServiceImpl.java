package com.coffeeshop.service.product;

import com.coffeeshop.exception.*;
import com.coffeeshop.model.entity.*;
import com.coffeeshop.model.web.productDetails.CharacteristicResponse;
import com.coffeeshop.model.web.productDetails.InStockResponse;
import com.coffeeshop.model.web.productDetails.RichProductResponse;
import com.coffeeshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;
    private final ProductQuantityRepository productQuantityRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductCoffeeRepository productCoffeeRepository;
    private final ProductItemRepository productItemRepository;


    @Autowired
    public ProductSearchServiceImpl(ProductRepository productRepository,
                                    ProductQuantityRepository productQuantityRepository,
                                    ProductImageRepository productImageRepository,
                                    ProductCoffeeRepository productCoffeeRepository,
                                    ProductItemRepository productItemRepository) {

        this.productRepository = productRepository;
        this.productQuantityRepository = productQuantityRepository;
        this.productImageRepository = productImageRepository;
        this.productCoffeeRepository = productCoffeeRepository;
        this.productItemRepository = productItemRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public RichProductResponse findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        ProductQuantity productQuantity = productQuantityRepository.findProductQuantityByProduct(product)
                .orElseThrow(ProductQuantityNotFoundException::new);

        List<ProductImage> productImages = productImageRepository.findProductImagesByProduct(product)
                .orElseThrow(ProductImageNotFoundException::new);

        ProductItem productItem = productItemRepository.findProductItemByProduct(product)
                .orElseThrow(ProductItemNotFoundException::new);

        ProductCoffee productCoffee = productCoffeeRepository.findProductCoffeeByProduct(product)
                .orElseThrow(ProductCoffeeNotFoundException::new);

        return convertProductToRichProductResponse(product, productQuantity,
                productCoffee, productImages, productItem);
    }

    @Override
    public List<RichProductResponse> searchProductByParameters() {
        List<Long> ids = productRepository.getAllIds().orElseThrow(ProductNotFoundException::new);
        return ids.stream()
                .map(this::findProductById)
                .collect(Collectors.toList());
    }

    private RichProductResponse convertProductToRichProductResponse(Product product, ProductQuantity productQuantity,
                                                                    ProductCoffee productCoffee,
                                                                    List<ProductImage> productImages,
                                                                    ProductItem productItem) {

        CharacteristicResponse characteristicResponse = CharacteristicResponse.builder()
                .bitter(productCoffee.getBitter())
                .sour(productCoffee.getSour())
                .strong(productCoffee.getStrong())
                .build();

        Integer quantityAvailable = productItem.getWeightKg() * productQuantity.getQuantity();

        InStockResponse inStockResponse = InStockResponse.builder()
                .isAvailable(product.isAvailable())
                .quantityAvailable(quantityAvailable)
                .build();

        String[] imageLinks = createImageLinks(product.getId(), productImages.size());


        return RichProductResponse.builder()
                .id(product.getId())
                .unitPrice(product.getUnitPrice())
                .description(product.getDescription())
                .characteristicResponse(characteristicResponse)
                .inStockResponse(inStockResponse)
                .productName(product.getProductName())
                .quantityAvailableKg(productItem.getWeightKg())
                .productImages(imageLinks)
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

