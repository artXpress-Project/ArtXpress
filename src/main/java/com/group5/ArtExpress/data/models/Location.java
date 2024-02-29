package com.group5.ArtExpress.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Location {

    @Column(nullable = false, name="state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(nullable = false, name="lga")
    private String lga;

    @Column(nullable = false, name="address")
    private String address;
}
