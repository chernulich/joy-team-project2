package com.coffeeshop.service.order;

import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.entity.OrderDetails;
import com.coffeeshop.model.entity.OrderPrice;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.admin.OrderDetailsComments;
import com.coffeeshop.model.web.order.RichOrderDetailsResponse;
import com.coffeeshop.repository.*;
import com.coffeeshop.repository.admin.OrderDetailsCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailsAdminServiceImpl implements OrderDetailsAdminService{

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsCommentsRepository orderDetailsCommentsRepository;
    private final OrderPriceRepository orderPriceRepository;
    private final ProductRepository productRepository;
    private final ProductCoffeeRepository productCoffeeRepository;

    @Autowired
    public OrderDetailsAdminServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                                        OrderDetailsCommentsRepository orderDetailsCommentsRepository, OrderPriceRepository orderPriceRepository,
                                        ProductRepository productRepository, ProductCoffeeRepository productCoffeeRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderDetailsCommentsRepository = orderDetailsCommentsRepository;
        this.orderPriceRepository = orderPriceRepository;
        this.productRepository = productRepository;
        this.productCoffeeRepository = productCoffeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public RichOrderDetailsResponse getOrderDetails(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
        OrderDetails orderDetails = orderDetailsRepository.findByOrder(order);
        OrderPrice orderPrice = orderPriceRepository.findByOrder(order);
        List<OrderDetailsComments> orderDetailsComments = orderDetailsCommentsRepository.findByOrder(order);

        return null;
    }
}
