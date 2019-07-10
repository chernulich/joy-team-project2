package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacteristicsRequest {

    private Integer bitterFrom;
    private Integer bitterTo;
    private Integer sourFrom;
    private Integer sourTo;
    private Integer strongFrom;
    private Integer strongTo;
    private Boolean decaf;
    private Boolean ground;
    private String coffeeType;

}
