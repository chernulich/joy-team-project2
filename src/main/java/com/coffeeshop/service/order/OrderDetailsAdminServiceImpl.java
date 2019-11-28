package com.coffeeshop.service.order;

import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.common.CoffeeType;
import com.coffeeshop.model.entity.*;
import com.coffeeshop.model.entity.admin.OrderDetailsComments;
import com.coffeeshop.model.web.order.*;
import com.coffeeshop.repository.*;
import com.coffeeshop.repository.admin.OrderDetailsCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDetailsAdminServiceImpl implements OrderDetailsAdminService{

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsCommentsRepository orderDetailsCommentsRepository;
    private final OrderPriceRepository orderPriceRepository;
    private final ProductCoffeeRepository productCoffeeRepository;
    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderDetailsAdminServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                                        OrderDetailsCommentsRepository orderDetailsCommentsRepository,
                                        OrderPriceRepository orderPriceRepository,
                                        ProductCoffeeRepository productCoffeeRepository, OrderItemsRepository orderItemsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderDetailsCommentsRepository = orderDetailsCommentsRepository;
        this.orderPriceRepository = orderPriceRepository;
        this.productCoffeeRepository = productCoffeeRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public RichOrderDetailsResponse getOrderDetails(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
        OrderDetails orderDetails = orderDetailsRepository.findByOrder(order);
        OrderPrice orderPrice = orderPriceRepository.findByOrder(order);
        List<OrderDetailsComments> orderDetailsComments = orderDetailsCommentsRepository.findByOrder(order);
        List<OrderItems> orderItems = orderItemsRepository.findAllByOrder(order);
        List<ProductItem> productItems = orderItems.stream()
                .map(orderItem -> orderItem.getProductItemId())
                .collect(Collectors.toList());
        Map<Product, Integer> productsAndQuantity = new HashMap<>();
        productItems.forEach(productItem -> {
            if (productsAndQuantity.containsKey(productItem.getProduct())) {
                productsAndQuantity.put(productItem.getProduct(), productsAndQuantity.get(productItem.getProduct()) + 1);
            } else productsAndQuantity.put(productItem.getProduct(), 1);
        });
        Map<Product, CoffeeType> productAndCoffeeType = new HashMap<>();
        productsAndQuantity.keySet().forEach(product -> {
            CoffeeType type = productCoffeeRepository.findProductCoffeeByProduct(product).get().getCoffeeType();
            productAndCoffeeType.put(product, type);
        });
        return getRichOrderDetailsResponseFromDataBaseData(order, orderDetails, orderPrice, orderDetailsComments,
                productsAndQuantity, productAndCoffeeType);
    }

    private RichOrderDetailsResponse getRichOrderDetailsResponseFromDataBaseData(
            Orders order, OrderDetails orderDetails, OrderPrice orderPrice, List<OrderDetailsComments> orderDetailsComments,
            Map<Product, Integer> productsAndQuantity, Map<Product, CoffeeType> productAndCoffeeType) {

        CustomerInfo customerInfo = CustomerInfo.builder()
                .entityName(orderDetails.getCustomerName())
                .email(orderDetails.getOrderEmail())
                .phoneNumber(orderDetails.getCustomerPhoneNumber())
                .contactInfo(ContactInfo.builder()
                        .firstName(orderDetails.getContactFirstName())
                        .lastName(orderDetails.getContactLastName())
                        .phoneNumber(orderDetails.getContactPhoneNumber()).build()).build();
        Delivery delivery = Delivery.builder()
                .deliveryComment(orderDetails.getDeliveryComment())
                .apartment(orderDetails.getApartment())
                .city(orderDetails.getCity())
                .floor(orderDetails.getFloor())
                .houseNumber(orderDetails.getHouseNumber())
                .officialName(orderDetails.getCustomerName())
                .street(orderDetails.getStreet()).build();
        Total total = Total.builder()
                .subTotal(Double.valueOf(orderPrice.getSubtotalPrice()))
                .shipping(orderPrice.getShippingPrice())
                .orderTotal(Double.valueOf(orderPrice.getSubtotalPrice()) + orderPrice.getShippingPrice()).build();
        OrderDetailsResponse orderDetailsResponse = OrderDetailsResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getCreatedOn())
                .orderTotal(total.getOrderTotal())
                .orderPaymentStatus(order.getOrderPaymentStatus())
                .orderStatus(order.getOrderStatus())
                .orderTransitStatus(order.getOrderTransitStatus()).build();
        List<ProductResponse> productResponseList = productsAndQuantity.entrySet().stream()
                .map(entry -> ProductResponse.builder()
                            .productId(entry.getKey().getId())
                            .unitPrice(entry.getKey().getUnitPrice())
                            .productName(entry.getKey().getProductName())
                            .quantity(entry.getValue())
                            .price(entry.getKey().getUnitPrice() * entry.getValue())
                            .type(productAndCoffeeType.get(entry.getKey()))
                            .productUrl("/api/customer/products/{" + entry.getKey().getId() +"}")
                        .build()).collect(Collectors.toList());
        List<OrderDetailsCommentsInfo> orderDetailsCommentsInfo = orderDetailsComments.stream()
                .map(orderDetailsComment -> {
                    String[] fullName = orderDetailsComment.getManagementUser().getFullName().split(" ");
                    return OrderDetailsCommentsInfo.builder()
                        .comment(orderDetailsComment.getComment())
                        .commentId(orderDetailsComment.getId())
                        .createdOn(orderDetailsComment.getCreatedOn())
                        .managementUserInfo(ManagementUserInfo.builder()
                                .firstName(fullName[0])
                                .lastName(fullName[1])
                                .avatarImage(orderDetailsComment.getManagementUser().getAvatarImage()).build())
                        .build();
                }).collect(Collectors.toList());

        return RichOrderDetailsResponse.builder()
                .customerInfo(customerInfo)
                .delivery(delivery)
                .comments(orderDetailsCommentsInfo)
                .total(total)
                .orderDetails(orderDetailsResponse)
                .products(productResponseList).build();
    }
}
