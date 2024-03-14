package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "artwork")
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artworkId;

    @Column(nullable = false, name="title")
    private String title;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
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
    private Long price;

    @Column(nullable = true, name="comment")
    @OneToMany(mappedBy = "artwork",cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true, name="likes")
    private int likes;

    private boolean available;

    @Column(nullable = true, name="uploadDateTime")
    private LocalDateTime uploadDateTime;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

}
