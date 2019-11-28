package com.coffeeshop.service.order;

import com.coffeeshop.model.admin.CommentRequest;

public interface OrderDetailsCommentsService {

    void addComment(Long orderId, CommentRequest commentRequest);

    void deleteComment(Long orderId, Long commentId);
}
