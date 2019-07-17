package com.coffeeshop.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseDate extends BaseIdentification {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createOn;

    @UpdateTimestamp
    private LocalDateTime updateOn;
}
