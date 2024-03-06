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

    @JoinColumn(nullable = true, name="genre")
    @ManyToOne
    private Genre genre;

    @JoinColumn(nullable = false, name="description")
    private String description;
    @ManyToOne
    private Artist artist;

    @Column(nullable = false, name="medium")
    private String medium;

    @Column(nullable = false, name="size")
    private String size;

    @Column(nullable = false, name="bigDecimal")
    private String BigDecimal;

    @Column(nullable = true, name="comment")
    @OneToMany
    private List<Comment> comments;

    @Column(nullable = true, name="likes")
    private int likes;

    @Column(nullable = false, name="uploadDateTime")
    private LocalDateTime uploadDateTime;

    @Column(nullable = true)
    private StringBuffer imageLinks;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

}
