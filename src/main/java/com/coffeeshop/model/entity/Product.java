package com.coffeeshop.model.entity;

import com.coffeeshop.model.entity.converter.ProductCategoryConverter;
import com.coffeeshop.model.entity.type.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

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

    @Column(name = "AVAILABLE")
    private boolean available;

    @Column(name = "VERSION")
    @Version
    private Integer version;

}