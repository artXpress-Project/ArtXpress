package com.group5.ArtExpress.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group5.ArtExpress.dto.ArtworkDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "collector")
public class Collector extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="phoneNumber")
    private String phoneNumber;


    @Column(nullable = false, name="dateTime")
    private LocalDateTime dateTime;

    @Column(nullable = false, name="isEnabled")
    private boolean isEnabled;

    @Column(nullable = false, name = "isLocked")
    private boolean isLocked;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryAddress> addresses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "collector")
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<ArtworkDto>favourite = new ArrayList<>();
}
