package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "artwork")
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artworkId;

    @Column(nullable = false, name="title")
    private String title;

    @Column(nullable = false, name="genre")
    @ManyToOne
    private Genre genre;

    @Column(nullable = false, name="description")
    private String description;
    @ManyToOne
    private Artist artist;

    @Column(nullable = false, name="medium")
    private String medium;

    @Column(nullable = false, name="size")
    private String size;

    @Column(nullable = false, name="bigDecimal")
    private String BigDecimal;

    @Column(nullable = false, name="comment")
    @OneToMany
    private List<Comment> comments;

    @Column(nullable = false, name="likes")
    private int likes;

    @Column(nullable = false, name="uploadDateTime")
    private LocalDateTime uploadDateTime;
}