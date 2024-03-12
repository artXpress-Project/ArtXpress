package com.group5.ArtExpress.data.models;

import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "artist")
public class Artist extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,
            name="businessName",
            unique = true
    )
    private String businessName;

    @Column(nullable = false, name="bio")
    private String bio;

    @Column(nullable = false,
            name = "phoneNumber",
            unique = true
    )
    private String phoneNumber;

    @Column(nullable = false, name = "location")
    @Embedded
    private Location location;

    @Column(nullable = true, name = "artworks")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artwork> artworks;

    @Column(nullable = false, name = "isEnabled")
    private boolean isEnabled;

    @Column(nullable = false, name = "dateTime")
    private LocalDateTime dateTime;

    @Column(nullable = false, name = "isLocked")
    private boolean isLocked;

    @OneToOne(cascade = CascadeType.REMOVE)
    private ArtistConfirmation artistConfirmation;

    private String twitter;
    private String linkDn;
    private String instagram;

    @ElementCollection
    @Column(length = 1000)
    private List<String> profileImages ;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


}
