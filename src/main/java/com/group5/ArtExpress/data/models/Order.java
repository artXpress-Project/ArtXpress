package com.group5.ArtExpress.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    private Artist artist;
    private Long orderId;
    private String address;

    private Long totalAmount;
    @ManyToOne
    private Collector collector;
    private String orderStatus;
    private Date createdAt;
    @ManyToOne
    private DeliveryAddress deliveryAddresses;
    private Long totalPrice;
    @OneToMany
    private List<OrderItem> items;
}
