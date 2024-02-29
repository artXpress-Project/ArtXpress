package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.confirmation.CollectorConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorConfirmationRepo extends JpaRepository<CollectorConfirmation, Long> {
    CollectorConfirmation findByToken(String token);
}
