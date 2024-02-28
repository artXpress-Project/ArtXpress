package com.group5.ArtExpress.data.models;


import lombok.Data;


@Data
public abstract class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
