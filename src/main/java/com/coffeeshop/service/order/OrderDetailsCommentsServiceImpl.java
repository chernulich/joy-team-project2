package com.coffeeshop.service.order;

import com.coffeeshop.exception.comments.CommentsException;
import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.exception.user.UsersException;
import com.coffeeshop.model.admin.CommentRequest;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.admin.ManagementUsers;
import com.coffeeshop.model.entity.admin.OrderDetailsComments;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.repository.admin.ManagementUsersRepository;
import com.coffeeshop.repository.admin.OrderDetailsCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailsCommentsServiceImpl implements OrderDetailsCommentsService{

    private final OrderRepository orderRepository;
    private final OrderDetailsCommentsRepository orderDetailsCommentsRepository;
    private final ManagementUsersRepository managementUsersRepository;

    @Autowired
    public OrderDetailsCommentsServiceImpl(OrderRepository orderRepository, OrderDetailsCommentsRepository orderDetailsCommentsRepository,
                                           ManagementUsersRepository managementUsersRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsCommentsRepository = orderDetailsCommentsRepository;
        this.managementUsersRepository = managementUsersRepository;
    }


    @Override
    @Transactional
    public void addComment(Long orderId, CommentRequest commentRequest) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
        ManagementUsers user = managementUsersRepository.findById(1L)
                .orElseThrow(UsersException :: new);
        OrderDetailsComments comment = OrderDetailsComments.builder()
                .managementUser(user)
                .order(order)
                .comment(commentRequest.getComment()).build();
        orderDetailsCommentsRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long orderId, Long commentId) {
        orderDetailsCommentsRepository.deleteById(commentId);
    }
}
