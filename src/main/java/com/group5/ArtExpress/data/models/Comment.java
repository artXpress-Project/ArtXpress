package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

//    @Column(nullable = false, name="collectorId")
//    private Long collectorId;
//
//    @Column(nullable = false, name="aetworkId")
//    private Long artworkId;

    @ManyToOne
    private Artwork artwork;

    @Column(nullable = false, name="message")
    private String commentMessage;

    @Column(nullable = false, name="dateTime")
    private LocalDateTime dateTime;


}
