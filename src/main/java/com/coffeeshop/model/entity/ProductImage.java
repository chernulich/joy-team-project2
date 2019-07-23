package com.coffeeshop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImage extends BaseDate {

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @Column(name = "IMAGE", nullable = false)
    private byte[] image;

    @Builder
    public ProductImage(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, Product product, byte[] image) {
        super(id, createdOn, updatedOn);
        this.product = product;
        this.image = image;
    }
}
