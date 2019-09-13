package com.coffeeshop.model.web.checkout;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ApiModel
public class DeliveryRequest {

    @ApiModelProperty(example = "Pedro production")
    private String officialName;

    @ApiModelProperty(example = "Minsk")
    private String city;

    @ApiModelProperty(example = "Pica")
    private String street;

    @ApiModelProperty(example = "13")
    private String houseNumber;

    @ApiModelProperty(example = "6")
    private String apartment;

    @ApiModelProperty(example = "5")
    private Integer floor;

    @ApiModelProperty(example = "Мертвец весь день трудится над докладом. " +
                                "Присутствие кончается. И вот - " +
                                "Нашептывает он, виляя задом, " +
                                "Сенатору скабрезный анекдот...")
    private String deliveryComment;
}
