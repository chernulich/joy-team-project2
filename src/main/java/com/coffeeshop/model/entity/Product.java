package com.coffeeshop.model.entity;

import com.coffeeshop.model.entity.converter.ProductCategoryConverter;
import com.coffeeshop.model.entity.type.ProductCategory;
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
@Table(name = "PRODUCT")
public class Product extends BaseDate {

    @Column(name = "SHORT_DESCRIPTION", length = 100, nullable = false)
    private String shortDescription;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PREVIEW_IMAGE", nullable = false)
    private String previewImage;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "PRODUCT_CATEGORY_ID")
    @Convert(converter = ProductCategoryConverter.class)
    private ProductCategory productCategory;

    @Column(name = "AVAILABLE", nullable = false)
    private boolean available;

    @Column(name = "VERSION")
    @Version
    private Integer version;

    @Builder
    public Product(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, String shortDescription,
                   String description, String previewImage, Double unitPrice,
                   ProductCategory productCategory, boolean available, Integer version) {
        super(id, createdOn, updatedOn);
        this.shortDescription = shortDescription;
        this.description = description;
        this.previewImage = previewImage;
        this.unitPrice = unitPrice;
        this.productCategory = productCategory;
        this.available = available;
        this.version = version;
    }
}
