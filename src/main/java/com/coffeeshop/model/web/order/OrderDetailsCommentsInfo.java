package com.coffeeshop.model.web.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderDetailsCommentsInfo {

    private Long commentId;
    private String comment;
    private LocalDateTime createdOn;
    private ManagementUserInfo managementUserInfo;

}
