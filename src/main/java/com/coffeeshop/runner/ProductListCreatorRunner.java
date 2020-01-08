package com.coffeeshop.runner;

import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductCoffee;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.model.entity.admin.ManagementUsers;
import com.coffeeshop.model.entity.converter.CoffeeTypeConverter;
import com.coffeeshop.model.entity.type.ProductCategory;
import com.coffeeshop.model.web.checkout.*;
import com.coffeeshop.repository.ProductCoffeeRepository;
import com.coffeeshop.repository.ProductQuantityRepository;
import com.coffeeshop.repository.ProductRepository;
import com.coffeeshop.repository.admin.ManagementUsersRepository;
import com.coffeeshop.service.checkout.CheckoutService;
import com.coffeeshop.service.item.ProductItemManagementService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

@Component
@Profile("dev")
public class ProductListCreatorRunner implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductCoffeeRepository productCoffeeRepository;
    private final ProductQuantityRepository productQuantityRepository;
    private final ProductItemManagementService productItemManagementService;
    private final ManagementUsersRepository managementUsersRepository;
    private final CheckoutService checkoutService;


    @Autowired
    public ProductListCreatorRunner(ProductRepository productRepository, ProductCoffeeRepository productCoffeeRepository,
                                    ProductQuantityRepository productQuantityRepository, CheckoutService checkoutService,
                                    ProductItemManagementService productItemManagementService,
                                    ManagementUsersRepository managementUsersRepository) {
        this.productRepository = productRepository;
        this.productCoffeeRepository = productCoffeeRepository;
        this.productQuantityRepository = productQuantityRepository;
        this.productItemManagementService = productItemManagementService;
        this.managementUsersRepository = managementUsersRepository;
        this.checkoutService = checkoutService;
    }


    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        List<Product> products = new ArrayList<>();
        List<ProductCoffee> productCoffees = new ArrayList<>();
        List<ProductQuantity> productQuantities = new ArrayList<>();
        List<ProductItemRequest> productItemRequests = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Product product = Product.builder()
                    .productName("product#" + i)
                    .available(true)
                    .unitPrice(random.nextDouble() * 200)
                    .shortDescription("This is short description of product#" + i)
                    .description("This is short description of product#" + i)
                    .productCategory(ProductCategory.COFFEE)
                    .previewImage("")
                    .build();

            ProductCoffee productCoffee = ProductCoffee.builder()
                    .ground(random.nextBoolean())
                    .decaf(random.nextBoolean())
                    .bitter(random.nextInt(5) + 1)
                    .sour(random.nextInt(5) + 1)
                    .strong(random.nextInt(5) + 1)
                    .coffeeType(new CoffeeTypeConverter().convertToEntityAttribute(random.nextInt(4)+1))
                    .product(product)
                    .build();
            productCoffees.add(productCoffee);
            products.add(product);

            ProductQuantity productQuantity= ProductQuantity.builder()
                    .quantity(random.nextInt(100))
                    .product(product)
                    .build();
            productQuantities.add(productQuantity);

            Long id = 1L;
            ProductItemRequest productItemRequest = ProductItemRequest.builder()
                    .productId(id + i)
                    .weightKg(random.nextInt(100))
                    .build();
            productItemRequests.add(productItemRequest);
        }

        productRepository.saveAll(products);
        productQuantityRepository.saveAll(productQuantities);
        productCoffeeRepository.saveAll(productCoffees);
        productItemManagementService.createProductItems(productItemRequests);

        InputStream io = ProductListCreatorRunner.class.getResourceAsStream("/image/avatar/sad-face-icon.jpg");
        String defaultAvatar = Base64.getEncoder().encodeToString(IOUtils.toByteArray(io));
        ManagementUsers user = managementUsersRepository.findById(1L).get();
        user.setAvatarImage(defaultAvatar);
        managementUsersRepository.save(user);

        List<ProductWeightQuantityRequest> list = new ArrayList<>();
        list.add(ProductWeightQuantityRequest.builder().productId(1L).quantity(10).build());
        list.add(ProductWeightQuantityRequest.builder().productId(2L).quantity(6).build());
        CheckoutRequest request = CheckoutRequest.builder()
                .customerInfo(CustomerInfoRequest.builder()
                        .email("chernulich.alex@gmail.com")
                        .phoneNumber("123456")
                        .entityName("EntityName")
                        .contacts(ContactInfoRequest.builder()
                                .firstName("Vasya")
                                .lastName("Pupkin")
                                .phoneNumber("321654")
                                .build()).build())
                .delivery(DeliveryRequest.builder()
                        .apartment("2B")
                        .city("NY")
                        .deliveryComment("lkkjhg")
                        .floor("123")
                        .houseNumber("5d5d5")
                        .officialName("name")
                        .selfPickup(true)
                        .street("street")
                        .build())
                .products(list).build();

        checkoutService.checkout(request);

    }
}
