package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;


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

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime dateTime;

    @Column(nullable = false, name="isEnabled")
    private boolean isEnabled;
}
