package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "exhibition")
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionEventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name="phone_Number", nullable = false, unique = true)
    private String phoneNumber;
    private boolean isEnabled;
    private LocalDateTime dateRegistered;


}
