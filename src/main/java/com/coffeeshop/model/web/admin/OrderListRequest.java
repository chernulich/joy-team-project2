package com.coffeeshop.model.web.admin;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderListRequest {

    //todo What is date from and to?

    @Positive
    private Integer page;

    @Positive
    private Integer result;

    @Length(min = 3, max = 50)
    private String query;

    @Past
    private LocalDateTime from;

    private LocalDateTime to;
}
