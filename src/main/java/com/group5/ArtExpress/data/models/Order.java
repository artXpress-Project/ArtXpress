package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Long totalAmount;
    @ManyToOne
    private Collector collector;
    private String orderStatus;
    private Date createdAt;
    private int totalItem;
    private int totalPrice;
    @OneToMany
    private List<OrderItem> items;
}
