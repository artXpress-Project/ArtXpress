package com.group5.ArtExpress.data.models;


import jakarta.persistence.*;
import lombok.Data;


@Data
@MappedSuperclass
public abstract class User {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
