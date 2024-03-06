package com.group5.ArtExpress.confirmation;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.ExhibitionEventRegistration;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "exhibition-confirmation")
public class ExhibitionConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate;

    private String token;
    @OneToOne(targetEntity = ExhibitionEventRegistration.class, fetch = FetchType.EAGER)
    private ExhibitionEventRegistration exhibitionEventRegistration;

    public ExhibitionConfirmation(ExhibitionEventRegistration exhibitionEventRegistration) {
        this.exhibitionEventRegistration = exhibitionEventRegistration;
        this.createdDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }
}
