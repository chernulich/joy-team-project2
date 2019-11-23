package com.coffeeshop.model.web.admin;

import lombok.*;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderListRequest {

    //todo What is date from and to?

    @Min(1)
    private Integer page;

    @Min(1)
    private Integer result;

    private String query;

    private LocalDateTime from;

    private LocalDateTime to;
}
