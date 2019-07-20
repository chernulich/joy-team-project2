package com.coffeeshop.model.entity;

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
@Table(name = "ORDER_DETAILS")
public class OrderDetails extends BaseDate{

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID" , nullable = false)
    private Orders order;

    @Column(name = "CUSTOMER_ENTITY_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_ENTITY_PHONE_NUMBER")
    private String customerPhoneNumber;

    @Column(name = "ORDER_EMAIL", nullable = false)
    private String orderEmail;

    @Column(name = "CONTACT_FIRST_NAME", nullable = false)
    private String contactFirstName;

    @Column(name = "CONTACT_LAST_NAME", nullable = false)
    private String contactLastName;

    @Column(name = "CONTACT_PHONE_NUMBER", nullable = false)
    private String contactPhoneNumber;

    @Column(name = "IS_SELF_PICKUP")
    private Boolean isSelfPickup;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    @Column(name = "APARTMENT")
    private String apartment;

    @Column(name = "FLOOR")
    private String floor;

    @Column(name = "DELIVERY_COMMENT")
    private String deliveryComment;


}

