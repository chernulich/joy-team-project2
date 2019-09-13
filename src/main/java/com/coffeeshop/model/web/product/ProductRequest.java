package com.coffeeshop.model.web.product;

import com.coffeeshop.model.web.DefaultDtoValues;
import com.coffeeshop.util.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ProductRequest {

    private static DefaultDtoValues defaultDtoValues;

    static {
        defaultDtoValues = BeanUtil.getBean(DefaultDtoValues.class);
    }

    @ApiModelProperty(example = "1")
    @Setter(AccessLevel.NONE)
    @Min(1)
    private Integer page;

    @ApiModelProperty(example = "10")
    @Setter(AccessLevel.NONE)
    @Min(1)
    private Integer results;

    @ApiModelProperty(example = "Ala")
    private String search;

    @ApiModelProperty(example = "10")
    private Double priceMin;

    @ApiModelProperty(example = "100")
    private Double priceMax;

    @ApiModelProperty(example = "price")
    private String sortBy;

    private CharacteristicsRequest characteristics;

    public void setPage(Integer page) {
        if (page == null) {
            this.page = defaultDtoValues.getDefaultPageSize();
        } else this.page = page;
    }

    public void setResults(Integer results) {
        if (results == null) {
            this.results = defaultDtoValues.getDefaultResultSize();
        } else if (results > 20) {
            this.results = defaultDtoValues.getDefaultMaxResultSize();
        } else this.results = results;
    }
}
