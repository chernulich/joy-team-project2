package com.coffeeshop.service.checkout;

import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.model.entity.*;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import com.coffeeshop.model.web.checkout.CheckoutRequest;
import com.coffeeshop.model.web.checkout.CheckoutResponse;
import com.coffeeshop.model.web.checkout.ProductWeightQuantityRequest;
import com.coffeeshop.repository.*;
import com.coffeeshop.service.email.OrderEmailConfirmationTemplate;
import com.coffeeshop.service.email.OrderEmailSendService;
import com.coffeeshop.service.item.ProductItemManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@PropertySource(value = "classpath:custom-application.properties")
public class CheckoutServiceImpl implements CheckoutService {
    @Value("${checkout.message}")
    private String CHECKOUT_MESSAGE;

    private final ProductItemManagementService productItemManagementService;
    private final ProductItemRepository productItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderPriceRepository orderPriceRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderEmailConfirmationTemplate orderEmailConfirmationTemplate;
    private final OrderEmailSendService orderEmailSendService;

    @Autowired
    public CheckoutServiceImpl(ProductItemManagementService productItemManagementService,
                               ProductItemRepository productItemRepository, ProductRepository productRepository,
                               OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                               OrderPriceRepository orderPriceRepository, OrderItemsRepository orderItemsRepository,
                               OrderEmailConfirmationTemplate orderEmailConfirmationTemplate, OrderEmailSendService orderEmailSendService) {
        this.productItemManagementService = productItemManagementService;
        this.productItemRepository = productItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderPriceRepository = orderPriceRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.orderEmailConfirmationTemplate = orderEmailConfirmationTemplate;
        this.orderEmailSendService = orderEmailSendService;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW,
            noRollbackFor = {MessagingException.class, MailAuthenticationException.class, MailSendException.class})
    public CheckoutResponse checkout(CheckoutRequest request) {

        Orders order = Orders.builder()
                .orderPaymentStatus(OrderPaymentStatus.NO_INFO)
                .orderTransitStatus(OrderTransitStatus.NEW_ORDER)
                .orderStatus(OrderStatus.UNPROCESSED)
                .build();

        OrderDetails orderDetails = convertCheckoutRequestToOrderDetails(request, order);

        Map<Long, Integer> productIdAmountMap = request.getProducts().stream()
                .collect(Collectors.toMap(ProductWeightQuantityRequest::getProductId,
                        ProductWeightQuantityRequest::getQuantity));

        List<ProductItemResponse> markedAsSoldProductItems =
                productItemManagementService.findAndMarkAsSold(productIdAmountMap);

        Map<Product, Integer> productAmountMap = productIdAmountMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> productRepository.getOne(entry.getKey()), Map.Entry::getValue));

        List<Long> productItemIds = markedAsSoldProductItems
                .stream()
                .map(ProductItemResponse::getId)
                .collect(Collectors.toList());

        OrderPrice orderPrice = OrderPrice.builder()
                .order(order)
                .subtotalPrice(calculatedSubTotalPrice(productAmountMap))
                .build();

        List<ProductItem> productItems = productItemRepository.findAllById(productItemIds);

        List<OrderItems> orderItems = productItems.stream().map(item -> OrderItems.builder()
                .order(order)
                .productItemId(item)
                .build()).collect(Collectors.toList());

        Orders savedOrder = orderRepository.save(order);
        OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
        orderPriceRepository.save(orderPrice);
        orderItemsRepository.saveAll(orderItems);

        try {
            sendConfirmationEmail(savedOrder, savedOrderDetails);
        } catch (MailSendException | MailAuthenticationException ex) {
            ex.printStackTrace();
        }

        return CheckoutResponse.builder()
                .orderId(savedOrder.getId())
                .message(CHECKOUT_MESSAGE)
                .build();
    }

    private void sendConfirmationEmail(Orders savedOrder, OrderDetails savedOrderDetails) {
        OrderEmail orderEmail = orderEmailConfirmationTemplate.createOrderConfirmationEmail(
                savedOrderDetails.getOrderEmail(),
                savedOrderDetails.getContactFirstName(),
                savedOrderDetails.getContactLastName(),
                savedOrder.getId());
        try {
            orderEmailSendService.sendEmail(orderEmail);
        } catch (MailSendException | MailAuthenticationException ex) {
            ex.printStackTrace();
        }
    }

    private OrderDetails convertCheckoutRequestToOrderDetails(CheckoutRequest request, Orders order) {
        return OrderDetails.builder()
                .order(order)
                .customerName(request.getCustomerInfo().getEntityName())
                .customerPhoneNumber(request.getCustomerInfo().getPhoneNumber())
                .orderEmail(request.getCustomerInfo().getEmail())
                .contactFirstName(request.getCustomerInfo().getContacts().getFirstName())
                .contactLastName(request.getCustomerInfo().getContacts().getLastName())
                .contactPhoneNumber(request.getCustomerInfo().getContacts().getPhoneNumber())
                .isSelfPickup(request.getDelivery().isSelfPickup())
                .city(request.getDelivery().getCity())
                .street(request.getDelivery().getStreet())
                .houseNumber(request.getDelivery().getHouseNumber())
                .apartment(request.getDelivery().getApartment())
                .floor(request.getDelivery().getFloor())
                .deliveryComment(request.getDelivery().getDeliveryComment())
                .build();
    }

    private String calculatedSubTotalPrice(Map<Product, Integer> productAmountMap) {
        double sum = productAmountMap.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getUnitPrice() * entry.getValue())
                .sum();
        return Double.toString(sum);
    }
}
