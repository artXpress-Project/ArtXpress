package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "artist")
public class Artist extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="businessName")
    private String businessName;

    @Column(nullable = false, name="bio")
    private String bio;

    @Column(nullable = false, name = "phoneNumber")
    private String phoneNumber;

    @Column(nullable = false, name = "location")
    @Embedded
    private Location location;

    @Column(nullable = false, name = "artworks")
    @OneToMany
    private List<Artwork> artworks;

    @Column(nullable = false, name = "isEnabled")
    private boolean isEnabled;

    @Column(nullable = false, name = "dateTime")
    private LocalDateTime dateTime;
}