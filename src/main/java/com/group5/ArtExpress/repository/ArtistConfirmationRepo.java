package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistConfirmationRepo extends JpaRepository<ArtistConfirmation, Long> {
    ArtistConfirmation findByToken(String token);
}
