package com.group5.ArtExpress.confirmation;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name="artist-confirmation")
public class ArtistConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Artist.class, fetch = FetchType.EAGER)
    private Artist artist;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate;

    public ArtistConfirmation(Artist artist) {
        this.artist = artist;
        this.createdDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }
}
