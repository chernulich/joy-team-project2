package com.coffeeshop.controller.admin;

import com.coffeeshop.model.admin.CommentRequest;
import com.coffeeshop.service.order.OrderDetailsCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/orders")
public class OrderDetailsCommentsAdminController {

    private final OrderDetailsCommentsService orderDetailsCommentsService;

    @Autowired
    public OrderDetailsCommentsAdminController(OrderDetailsCommentsService orderDetailsCommentsService) {
        this.orderDetailsCommentsService = orderDetailsCommentsService;
    }

    @PostMapping("/{orderId}/comment")
    public void addComment(@PathVariable("orderId") Long orderId, @RequestBody @Valid CommentRequest commentRequest) {
        orderDetailsCommentsService.addComment(orderId, commentRequest);
    }

    @DeleteMapping("/{orderId}/comment/{commentId}")
    public void deleteComment(@PathVariable("orderId") Long orderId, @PathVariable("commentId") Long commentId) {
        orderDetailsCommentsService.deleteComment(orderId, commentId);
    }
}
