package com.coffeeshop.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@DynamicInsert
@Entity
@Table(name = "PRODUCT_QUANTITY")
public class ProductQuantity extends BaseDate{

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;


    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "VERSION")
    @Version
    private Long version;

    @Builder
    public ProductQuantity(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, Product product, Integer quantity,
                           Long version) {
        super(id, createdOn, updatedOn);
        this.product = product;
        this.quantity = quantity;
        this.version = version;
    }
}
