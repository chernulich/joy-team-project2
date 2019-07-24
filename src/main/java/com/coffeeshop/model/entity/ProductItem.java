package com.coffeeshop.model.entity;

import com.coffeeshop.model.entity.converter.ProductStatusConverter;
import com.coffeeshop.model.entity.type.ProductStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@DynamicInsert
@Entity
@Table(name = "PRODUCT_ITEM")
public class ProductItem extends BaseDate{

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @Column(name = "WEIGHT_KG", nullable = false)
    private Integer weightKg;

    @Column(name = "STATUS")
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatus productStatus;

    @Column(name = "VERSION")
    @Version
    private Integer version;

    @Builder
    public ProductItem(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, Product product, Integer weightKg,
                       ProductStatus productStatus, Integer version) {
        super(id, createdOn, updatedOn);
        this.product = product;
        this.weightKg = weightKg;
        this.productStatus = productStatus;
        this.version = version;
    }
}
