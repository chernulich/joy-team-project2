package com.coffeeshop.model.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass
public abstract class BaseIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
}
