package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepo extends JpaRepository<Artist, Long> {
    Artist findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);

}
