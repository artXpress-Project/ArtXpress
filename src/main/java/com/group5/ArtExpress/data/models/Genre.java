package com.group5.ArtExpress.data.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="genreName")
    private String genreName;

    @Column(nullable = false, name="genreDescription")
    private String genreDescription;


}
