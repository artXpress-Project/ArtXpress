package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.confirmation.ExhibitionConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionConfirmationRepo extends JpaRepository<ExhibitionConfirmation, Long> {
    ExhibitionConfirmation findByToken(String token);
}
