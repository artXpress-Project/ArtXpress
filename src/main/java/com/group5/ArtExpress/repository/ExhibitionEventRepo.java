package com.group5.ArtExpress.repository;


import com.group5.ArtExpress.data.models.ExhibitionEventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExhibitionEventRepo extends JpaRepository<ExhibitionEventRegistration, Long> {

    ExhibitionEventRegistration  findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
