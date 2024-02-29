package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="collectorId")
    private Long collectorId;

    @Column(nullable = false, name="aetworkId")
    private Long artworkId;

    @Column(nullable = false, name="message")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name="dateTime")
    private LocalDateTime dateTime;
}
