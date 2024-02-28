package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "collector")
public class Collector extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long collectorId;

    @Column(nullable = false, name="phoneNumber")
    private String phoneNumber;

    @Column(nullable = false, name="address")
    private String address;

    @Column(nullable = false, name="dateTime")
    private LocalDateTime dateTime;
}
