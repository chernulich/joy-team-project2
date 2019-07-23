package com.coffeeshop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDate extends BaseIdentification {

    @CreatedDate
    @Column(name = "CREATED_ON", updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "UPDATE_ON")
    private LocalDateTime updatedOn;

    public BaseDate(Long id, LocalDateTime createdOn, LocalDateTime updatedOn) {
        super(id);
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
}
