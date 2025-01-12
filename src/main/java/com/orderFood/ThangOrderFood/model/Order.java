package com.orderFood.ThangOrderFood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orderFood.ThangOrderFood.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private User customer;
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    private Date updatedAt;

    @ManyToOne
    private Address deliveryAddress;
    @OneToMany
    private List<OrderItem> items;

    private int totalItem;

    private int totalPrice;

 //   private Payment payment;
}
